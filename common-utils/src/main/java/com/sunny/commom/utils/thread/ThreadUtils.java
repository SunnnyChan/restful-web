package com.sunny.commom.utils.thread;

/*
 * Created by sunnnychan@outlook.com on 2019/1/21.
 */

import com.sunny.commom.utils.log.Log;

import java.util.Arrays;

public class ThreadUtils {
  public static void noExceptionSleep(int sleepTime) {
    try {
      Thread.sleep(sleepTime);
    } catch (InterruptedException ex) {
      Arrays.stream(ex.getStackTrace()).forEach(r -> Log.warn(r.toString()));
      Log.info("sleep exception, error : %s", ex.getMessage());
    }
  }

  public static void noExceptionSleep(Long startTime, int interval) {
    try {
      Long sleepTime = interval - (System.currentTimeMillis() - startTime);
      if (sleepTime > 0) {
        Thread.sleep(sleepTime);
      }
    } catch (InterruptedException ex) {
      Arrays.stream(ex.getStackTrace()).forEach(r -> Log.warn(r.toString()));
      Log.info("sleep exception, error : %s", ex.getMessage());
    }
  }
}
