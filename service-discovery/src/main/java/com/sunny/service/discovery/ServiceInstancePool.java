package com.sunny.service.discovery;

/*
 * Created by sunnnychan@outlook.com on 2019/1/21.
 */

import com.sunny.service.discovery.loadbalance.LoadBalancer;

public interface ServiceInstancePool {
  // get service instance by load balancer
  ServiceInstanceDetail getInstance(LoadBalancer rpcLoadBalance);

  // get service instance by id
  ServiceInstanceDetail getInstance(String id);

  // get all service instances
  ServiceInstanceDetail[] getAllInstances();
}