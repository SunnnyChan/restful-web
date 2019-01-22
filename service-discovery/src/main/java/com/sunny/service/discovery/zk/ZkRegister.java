package com.sunny.service.discovery.zk;

/*
 * Created by sunnnychan@outlook.com on 2019/1/22.
 */

import com.sunny.service.discovery.ServiceInstanceDetail;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.utils.CloseableUtils;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.UriSpec;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;

import java.io.Closeable;
import java.io.IOException;

public class ZkRegister implements Closeable {
  private final CuratorFramework client;
  private final String zkPath;
  private final ServiceInstance<ServiceInstanceDetail> serviceInstance;
  private final ServiceDiscovery<ServiceInstanceDetail> serviceDiscovery;


  /**
   * constructor.
   * @param client curator client
   * @param host host
   * @param port port
   * @param name service name
   * @throws Exception e
   */
  public ZkRegister(CuratorFramework client, String zkPath, String host, int port, String name)
      throws Exception {
    this.client = client;
    this.zkPath = zkPath;

    String id = String.format("%s:%s", host, port);

    serviceInstance = ServiceInstance.<ServiceInstanceDetail>builder()
        .name(name)
        .payload(new ServiceInstanceDetail(id, name))
        .uriSpec(new UriSpec("{scheme}://{address}:{port}"))
        .address(host)
        .port(port)
        .build();

    JsonInstanceSerializer<ServiceInstanceDetail> serializer
        = new JsonInstanceSerializer<ServiceInstanceDetail>(ServiceInstanceDetail.class);

    serviceDiscovery = ServiceDiscoveryBuilder.builder(ServiceInstanceDetail.class)
        .client(client)
        .basePath(zkPath)
        .serializer(serializer)
        .thisInstance(serviceInstance)
        .build();
  }


  public ServiceInstance<ServiceInstanceDetail> getInstance() {
    return serviceInstance;
  }

  public void start() throws Exception {
    serviceDiscovery.start();
  }

  @Override
  public void close() throws IOException {
    CloseableUtils.closeQuietly(serviceDiscovery);
  }
}
