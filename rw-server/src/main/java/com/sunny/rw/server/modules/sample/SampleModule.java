package com.sunny.rw.server.modules.sample;

/*
 * Created by sunnnychan@outlook.com  on 2019/1/11.
 */

import com.sunny.rw.server.modules.AbstractModule;

public class SampleModule extends AbstractModule {
  @Override
  protected void init() {
    this.name = "sample-module";
  }

  @Override
  protected void doWork() throws Exception {
    do {
      System.out.println(this.name + ": do nothing!");
      Thread.sleep(100000);
    } while (true);
  }
}
