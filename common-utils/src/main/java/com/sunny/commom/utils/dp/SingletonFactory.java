package com.sunny.commom.utils.dp;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/*
 * Created by sunnnychan@outlook.com on 2019/1/21.
 */
public class SingletonFactory {
  private static ConcurrentHashMap<String, Object> instances = new ConcurrentHashMap<>();

  /**
   * get instance by reflect
   *
   * @param clazz class
   * @param <T>   class
   * @return instance
   */
  public static synchronized <T> T get(Class<T> clazz) {
    T instance = null;
    try {
      String className = clazz.getName();
      instance = (T) instances.get(className);
      if (Objects.isNull(instance)) {
        instance = clazz.newInstance();
        instances.put(className, instance);
      }
    } catch (IllegalAccessException | InstantiationException e) {
      e.printStackTrace();
    }
    return instance;
  }

  public static synchronized <T> void destroy(Class<T> clazz) {
    T instance = (T) instances.get(clazz.getName());
    if (!Objects.isNull(instance)) {
      instances.remove(clazz.getName());
    }
  }

  public static ConcurrentHashMap<String, Object> getInstances() {
    return instances;
  }
}
