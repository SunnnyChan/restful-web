package com.sunny.service.discovery.zk;

/*
 * Created by sunnnychan@outlook.com on 2019/1/21.
 */

import com.sunny.service.discovery.ServiceInstanceDetail;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.utils.CloseableUtils;
import org.apache.curator.x.discovery.*;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;
import org.apache.curator.x.discovery.details.ServiceCacheListener;
import org.apache.curator.x.discovery.strategies.RandomStrategy;

import java.io.Closeable;
import java.io.IOException;
import java.util.Collection;

public class ZkDiscoverer<T> implements Closeable {
  private final ServiceDiscovery<ServiceInstanceDetail> serviceDiscovery;
  private final ServiceCache<ServiceInstanceDetail> serviceCache;
  private final ServiceProvider<ServiceInstanceDetail> serviceProvider;

  /**
   * constructor.
   *
   * @param client      curator client
   * @param serviceName service name
   * @throws Exception e
   */
  public ZkDiscoverer(CuratorFramework client, String zkPath, String serviceName)
      throws Exception {
    JsonInstanceSerializer<ServiceInstanceDetail> serializer = new
        JsonInstanceSerializer<>(ServiceInstanceDetail.class);

    this.serviceDiscovery = ServiceDiscoveryBuilder.builder(ServiceInstanceDetail.class)
        .client(client)
        .basePath(zkPath)
        .serializer(serializer)
        .build();
    serviceDiscovery.start();

    this.serviceCache = serviceDiscovery.serviceCacheBuilder()
        .name(serviceName)
        .build();

    this.serviceProvider = serviceDiscovery.serviceProviderBuilder()
        .serviceName(serviceName)
        .providerStrategy(new RandomStrategy<ServiceInstanceDetail>())
        .build();
  }

  /** start discoverer.
   *
   * @param listener a listener to handle node change
   * @throws Exception e
   */
  public void start(ServiceCacheListener listener) throws Exception {
    serviceProvider.start();
    serviceCache.addListener(listener);
    serviceCache.start();
  }

  public ServiceInstance<ServiceInstanceDetail> getInstance() throws Exception {
    return serviceProvider.getInstance();
  }

  public Collection<ServiceInstance<ServiceInstanceDetail>> getInstances() throws Exception {
    return serviceProvider.getAllInstances();
  }

  public ServiceCache<ServiceInstanceDetail> getServiceCache() {
    return serviceCache;
  }

  @Override
  public void close() throws IOException {
    CloseableUtils.closeQuietly(serviceCache);
    CloseableUtils.closeQuietly(serviceDiscovery);
  }
}
