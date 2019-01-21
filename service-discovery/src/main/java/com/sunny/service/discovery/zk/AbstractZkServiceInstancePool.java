package com.sunny.service.discovery.zk;

/*
 * Created by sunnnychan@outlook.com on 2019/1/21.
 */

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Sets;
import com.sunny.commom.utils.log.Log;
import com.sunny.service.discovery.ServiceInstanceDetail;
import com.sunny.service.discovery.ServiceInstancePool;
import com.sunny.service.discovery.loadbalance.LoadBalancer;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.x.discovery.ServiceCache;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.details.ServiceCacheListener;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractZkServiceInstancePool implements ServiceInstancePool {
  protected final CuratorFramework client;
  protected ZkDiscoverer discoverer;

  protected final ConcurrentHashMap<String, ServiceInstanceDetail> instances = new ConcurrentHashMap<>();

  public AbstractZkServiceInstancePool(String zkConnStr) throws Exception {
    client = CuratorFrameworkFactory.newClient(zkConnStr, 5 * 1000,
        2 * 1000, new RetryNTimes(5, 1000));
    client.start();
  }

  @Override
  public ServiceInstanceDetail getInstance(LoadBalancer loadBalancer) {
    try {
      return loadBalancer.choose(this.instances.values());
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public ServiceInstanceDetail getInstance(String id) {
    return (Objects.isNull(id) || id.isEmpty() || !this.instances.containsKey(id))
        ? null : this.instances.get(id);
  }

  @Override
  public ServiceInstanceDetail[] getAllInstances() {
    return this.instances.values().toArray(new ServiceInstanceDetail[]{});
  }

  protected class CacheListener implements ServiceCacheListener {
    private ServiceCache<ServiceInstanceDetail> serviceCache;
    protected final ConcurrentHashMap<String, ServiceInstanceDetail> instances;

    public CacheListener(ServiceCache<ServiceInstanceDetail> serviceCache,
                         ConcurrentHashMap<String, ServiceInstanceDetail> instances) {
      this.serviceCache = serviceCache;
      this.instances = instances;
    }

    @Override
    public void cacheChanged() {
      List<ServiceInstance<ServiceInstanceDetail>> serviceInstances = serviceCache.getInstances();
      Set<String> availableExecutors = Sets.newHashSet();
      for (ServiceInstance<ServiceInstanceDetail> instance : serviceInstances) {
        String id = String.format("%s://%s:%d", instance.getName(),
            instance.getAddress(), instance.getPort());
        availableExecutors.add(id);
        ServiceInstanceDetail serviceInstanceDetail = new ServiceInstanceDetail();
        serviceInstanceDetail.setId(id);
        serviceInstanceDetail.setName(instance.getName());
        if (!this.instances.containsKey(id)) {
          this.instances.put(id, serviceInstanceDetail);
          Log.info("instance added! id : %s, instance info : %s",
              id, JSON.toJSONString(instance));
        }
      }
      for (String id : instances.keySet()) {
        if (!availableExecutors.contains(id)) {
          instances.remove(id);
          Log.info("instance leaved! id : %s ", id);
        }
      }
    }

    @Override
    public void stateChanged(CuratorFramework client, ConnectionState newState) {
    }
  }

}
