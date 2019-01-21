package com.sunny.common.infra.redis;

/*
 * Created by sunnnychan@outlook.com on 2019/1/21.
 */

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Redis {

  public static JedisPool startJedisPool(String host, int port) {
    JedisPoolConfig poolConfig = new JedisPoolConfig();
    poolConfig.setMaxTotal(20);
    poolConfig.setTestOnBorrow(true);

    return new JedisPool(poolConfig, host, port, 5000);
  }

}