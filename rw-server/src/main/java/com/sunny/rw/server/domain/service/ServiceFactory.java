package com.sunny.rw.server.domain.service;

/*
 * Created by sunnnychan@outlook.com on 2019/1/21.
 */

import com.sunny.commom.utils.dp.SingletonFactory;
import com.sunny.commom.utils.log.Log;

import java.util.Objects;

public class ServiceFactory {
  /**
   * get service instance by reflect
   *
   * @param clazz class
   * @param <T>   class
   * @return instance
   */
  public static synchronized <T> T get(Class<T> clazz) {
    T object = SingletonFactory.get(clazz);
    if (!Objects.isNull(object)) {
      ((Service) object).start();
    }
    Log.error("create Object failed, Class Name : %s ", clazz.getName());
    return object;
  }

  public static synchronized <T> void destroy(Class<T> clazz) {
    SingletonFactory.destroy(clazz);
  }

  /**
   * close all service.
   */
  public static void close() {
    for (Object service : SingletonFactory.getInstances().values()) {
      if (service instanceof Service) {
        ((Service) service).close();
      }
    }
  }
}
