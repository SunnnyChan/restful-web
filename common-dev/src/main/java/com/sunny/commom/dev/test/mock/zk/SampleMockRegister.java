package com.sunny.commom.dev.test.mock.zk;

import com.sunny.service.discovery.ServiceInstanceDetail;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.test.TestingServer;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.UriSpec;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;

import java.io.File;
import java.util.Arrays;

/*
 * Created by sunnnychan@outlook.com on 2019/1/21.
 */
public class SampleMockRegister implements ZkMockRegister {
  private static final String moduleName = "SampleMockRegister";

  private CuratorFramework client;

  public static void main(String[] args) throws Exception {
    try {
      new TestingServer(2181, new File("run/zk/"));
      ZkMockRegister zkMockRegister = new  SampleMockRegister();
      zkMockRegister.register();
    } catch (Exception e) {
      System.out.println("==" + moduleName + "==" + e.getMessage());
      Arrays.stream(e.getStackTrace()).forEach(r -> System.out.println("==" + moduleName + "==" + r.toString()));
    }
  }

  public void register() throws Exception {
    this.client = new ZkClient().build();
    JsonInstanceSerializer<ServiceInstanceDetail> serializer
        = new JsonInstanceSerializer<ServiceInstanceDetail>(ServiceInstanceDetail.class);
    String name = "service";
    ServiceInstance<ServiceInstanceDetail> instance = ServiceInstance.<ServiceInstanceDetail>builder()
        .name(name)
        .payload(new ServiceInstanceDetail("10.121.10.19:8099", name))
        .uriSpec(new UriSpec("{scheme}://{address}:{port}"))
        .address("10.121.10.19")
        .port(8888)
        .build();
    try {
      this.client.getData().forPath("/data/service/e3e3f2df-702b-47bd-8baa-7f288fe48565");
      this.client.setData().forPath("/data/service/e3e3f2df-702b-47bd-8baa-7f288fe48565",
          serializer.serialize(instance));
    } catch (KeeperException.NoNodeException ex) {
      this.createNode("/data/service/e3e3f2df-702b-47bd-8baa-7f288fe48565",
          serializer.serialize(instance));
    }

  }

  private void createNode(String path, byte[] data) throws Exception {
    this.client.create().creatingParentsIfNeeded()//如果父节点没有自动创建
        //.withACL()设置权限 权限创建同原生API
        .withMode(CreateMode.PERSISTENT)//节点类型
        .forPath(path, data);
  }
}
