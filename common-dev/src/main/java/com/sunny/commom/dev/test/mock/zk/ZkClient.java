package com.sunny.commom.dev.test.mock.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/*
 * Created by sunnnychan@outlook.com on 2019/1/21.
 */
public class ZkClient {
  public CuratorFramework build() throws Exception {
    CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181",
        new ExponentialBackoffRetry(1000, 3));
    client.start();
    return client;
  }
}
