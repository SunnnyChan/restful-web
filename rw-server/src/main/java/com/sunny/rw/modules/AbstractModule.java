package com.sunny.rw.modules;

/*
 * Created by sunnnychan@outlook.com  on 2018/7/16.
 */


import com.sunny.commom.utils.conf.Log;

import java.util.Arrays;

public abstract class AbstractModule extends Thread implements ModuleInterface {
  protected String name;

  @Override
  public synchronized void start() {
    super.start();
    Log.info( "module : %s started ", this.name);
  }

  @Override
  public void run() {
    try {
      Log.init(null, 5000, "", this.name);
      this.init();
      while (true) {
        this.doWork();
        this.doFinalWork();
      }
    } catch (Exception ex) {
      Log.warn("exception type : %s, error message : %s", ex.getClass(), ex.getMessage());
      Arrays.stream(ex.getStackTrace()).forEach(r -> Log.warn(r.toString()));
    }
  }

  protected void init() {
    Log.info("module workflow init");
  }

  protected void doWork() throws Exception {
    Log.info("module workflow doWork");
  }

  protected void doFinalWork() {
    Log.info("module work flow doFinalWork");
  }

  @Override
  public void close() {
    Log.info("module : %s closed", this.name);
  }
}
