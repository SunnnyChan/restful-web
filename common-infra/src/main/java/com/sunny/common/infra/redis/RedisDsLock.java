package com.sunny.common.infra.redis;

/*
 * Created by sunnnychan@outlook.com on 2019/1/21.
 */

import com.sunny.commom.utils.log.Log;
import com.sunny.commom.utils.thread.ThreadUtils;
import redis.clients.jedis.Jedis;

public class RedisDsLock {
  private static final String LOCK_SUCCESS = "OK";
  private static final String SET_IF_NOT_EXIST = "NX";
  private static final String SET_WITH_EXPIRE_TIME = "PX";

  private static final int DEFAULT_SLEEP_TIME_WHEN_GET_LOCK_FAIL = 10; //ms
  private static final int TRY_TIMES_GIVE_A_LOG_WARN_WHEN_GET_LOCK = 20;

  public static boolean getLock(Jedis jedis, String key, String serviceObjectId, int expireTime,
                                int sleepTime, Long retryTime) {
    while (retryTime > 0) {
      if (tryGetLock(jedis, key, serviceObjectId, expireTime)) {
        return true;
      }
      ThreadUtils.noExceptionSleep(sleepTime);
      retryTime--;
    }
    Log.info("DsLock.getLock times beyond retryTime ：%s", retryTime);
    return false;
  }

  /**
   * poll to get a lock until success
   * @return time(ms) spent
   */
  public static long getLock(Jedis jedis, String key, String serviceObjectId, int expireTime) {
    final long start = System.currentTimeMillis();
    long retryTime = 0L;
    do {
      retryTime++;
      if (retryTime > TRY_TIMES_GIVE_A_LOG_WARN_WHEN_GET_LOCK) {
        Log.warn("DsLock.getLock too many times : %s", retryTime);
      }
      if (tryGetLock(jedis, key, serviceObjectId, expireTime)) {
        return System.currentTimeMillis() - start;
      }
      ThreadUtils.noExceptionSleep(DEFAULT_SLEEP_TIME_WHEN_GET_LOCK_FAIL);
    } while (true);
  }

  /**
   * try get distributed lock.
   *
   * @param jedis  redis client
   * @param key    redis set key，as lock flag
   * @param serviceObjectId  service object which lock belongs to
   * @param expireTime redis set expire time, as lock expire time
   * @return boolean
   */
  public static boolean tryGetLock(Jedis jedis, String key, String serviceObjectId, int expireTime) {
    String result = jedis.set(key, serviceObjectId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
    return LOCK_SUCCESS.equals(result);
  }

  /**
   * release lock.
   * 注意：
   * 比如客户端A加锁，一段时间之后客户端A解锁，在执行jedis.del()之前，锁突然过期了，
   * 此时客户端B尝试加锁成功，然后客户端A再执行del()方法，则将客户端B的锁给解除了，在业务代码层保证。
   *
   * @param jedis     redis client
   * @param key       redis set key，as lock flag
   * @param serviceObjectId service object which lock belongs to
   */
  public static void releaseLock(Jedis jedis, String key, String serviceObjectId) {
    if (serviceObjectId.equals(jedis.get(key))) {
      jedis.del(key);
    }
  }
}
