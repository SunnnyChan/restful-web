package com.sunny.service.discovery.loadbalance;

/*
 * Created by sunnnychan@outlook.com on 2019/1/21.
 */

import com.sunny.service.discovery.ServiceInstanceDetail;

import java.util.Collection;

public interface LoadBalancer {
  ServiceInstanceDetail choose(Collection<ServiceInstanceDetail> collection) throws Exception;
}
