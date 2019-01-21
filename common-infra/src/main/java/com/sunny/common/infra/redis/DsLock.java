package com.sunny.common.infra.redis;

/*
 * Created by sunnnychan@outlook.com on 2019/1/21.
 */

import redis.clients.jedis.Jedis;

public class DsLock {
  private static final String LOCK_SUCCESS = "OK";
  private static final String SET_IF_NOT_EXIST = "NX";
  private static final String SET_WITH_EXPIRE_TIME = "PX";

  /**
   * 尝试获取分布式锁.
   *
   * @param jedis      Redis客户端
   * @param lockKey    锁
   * @param requestId  请求标识
   * @param expireTime 超期时间
   * @return 是否获取成功
   */
  public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {
    String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
    return LOCK_SUCCESS.equals(result);
  }

  /**
   * 释放分布式锁. （注意：比如客户端A加锁，一段时间之后客户端A解锁，在执行jedis.del()之前，锁突然过期了，
   * 此时客户端B尝试加锁成功，然后客户端A再执行del()方法，则将客户端B的锁给解除了。）
   * 在业务代码层保证
   *
   * @param jedis     Redis客户端
   * @param lockKey   锁
   * @param requestId 请求标识
   */
  public static void releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {
    if (requestId.equals(jedis.get(lockKey))) {
      jedis.del(lockKey);
    }
  }
}
