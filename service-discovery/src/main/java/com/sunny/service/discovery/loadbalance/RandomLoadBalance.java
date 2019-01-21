package com.sunny.service.discovery.loadbalance;

/*
 * Created by sunnnychan@outlook.com on 2019/1/21.
 */

import com.sunny.commom.utils.thread.ThreadUtils;
import com.sunny.service.discovery.ServiceInstanceDetail;

import java.util.Collection;
import java.util.Random;

public class RandomLoadBalance implements LoadBalancer {
  private static int MAX_RETRY = 3;

  private final Random random = new Random();

  @Override
  public ServiceInstanceDetail choose(Collection<ServiceInstanceDetail> serviceInstanceDetails)
      throws Exception {
    return randomChoose(serviceInstanceDetails, 0);
  }

  private ServiceInstanceDetail randomChoose(Collection<ServiceInstanceDetail> serviceInstanceDetails,
                                          int retryTime) throws Exception {
    if (retryTime > MAX_RETRY) {
      throw new Exception("service random choose, max retry time, get available service failed");
    }
    if (serviceInstanceDetails.size() == 0) {
      retryTime++;
      ThreadUtils.noExceptionSleep(1000);
      randomChoose(serviceInstanceDetails, retryTime);
    }
    int idx = random.nextInt(serviceInstanceDetails.size());
    serviceInstanceDetails.toArray(new ServiceInstanceDetail[]{});
    return serviceInstanceDetails.toArray(serviceInstanceDetails.toArray(new ServiceInstanceDetail[]{}))[idx];
  }
}
