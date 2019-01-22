package com.sunny.rw.server.modules.sample;

/*
 * Created by sunnnychan@outlook.com on 2019/1/11.
 */

import com.sunny.commom.utils.module.AbstractModule;
import com.sunny.commom.utils.thread.ThreadUtils;

public class SampleModule extends AbstractModule {
  @Override
  protected void init() {
    this.name = "sample-module";
  }

  @Override
  protected void doWork() {
    do {
      System.out.println(this.name + ": do a poll print!");
      ThreadUtils.noExceptionSleep(100000);
    } while (true);
  }
}
