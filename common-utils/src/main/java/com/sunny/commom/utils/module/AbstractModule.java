package com.sunny.commom.utils.module;

/*
 * Created by sunnnychan@outlook.com  on 2018/7/16.
 */


import com.sunny.commom.utils.log.Log;
import com.sunny.commom.utils.log.LogInit;
import com.sunny.commom.utils.thread.ThreadUtils;

import java.util.Arrays;

public abstract class AbstractModule extends Thread implements ModuleInterface {
  protected String name;
  protected Integer sleepInterval;

  @Override
  public synchronized void start() {
    super.start();
    Log.info(this.name + " started!");
  }

  protected void init() {
  }

  @Override
  @SuppressWarnings("InfiniteLoopStatement")
  public void run() {
    try {
      LogInit.initLog(this.name);
      this.init();
      while (true) {
        LogInit.initLog(this.name);
        Long startTime = System.currentTimeMillis();

        this.doWork();

        this.finalWork(startTime);
      }
    } catch (Exception ex) {
      Log.error("dispatch job process error, %s", ex.getMessage());
      Arrays.stream(ex.getStackTrace()).forEach(r -> Log.warn(r.toString()));
    }
  }

  protected void doWork() {

  }

  protected void finalWork(Long startTime) {
    ThreadUtils.noExceptionSleep(startTime, this.sleepInterval);
  }

  @Override
  public void close() {
    Log.info(this.name + " closed!");
  }
}
