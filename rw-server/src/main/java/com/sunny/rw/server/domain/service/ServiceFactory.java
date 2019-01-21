package com.sunny.rw.server.domain.service;

/*
 * Created by sunnnychan@outlook.com on 2019/1/21.
 */

import jersey.repackaged.com.google.common.collect.Maps;

import java.util.Map;

public class ServiceFactory {
  private static Map<String, Object> serviceMap = Maps.newConcurrentMap();

  /**
   * get service instance by reflect
   *
   * @param clazz service interface class
   * @param <T>   interface
   * @return instance
   */
  public static synchronized <T> T get(Class<T> clazz) {
    T instance = null;
    try {
      instance = getInstance(clazz);
    } catch (IllegalAccessException | InstantiationException e) {
      e.printStackTrace();
    }
    return instance;
  }

  private static <T> T getInstance(Class<T> clazz) throws IllegalAccessException, InstantiationException {
    String serviceName = clazz.getName();
    T service = (T) serviceMap.get(serviceName);
    if (service == null) {
      service = clazz.newInstance();
      serviceMap.put(serviceName, service);
      ((Service) service).start();
    }
    return service;
  }

  /**
   * close all service.
   */
  public static void close() {
    for (Object service : serviceMap.values()) {
      ((Service) service).close();
    }
  }
}
