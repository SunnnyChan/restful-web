package com.sunny.rw.modules;

/*
 * Created by sunnnychan@outlook.com  on 2019/1/11.
 */

public class SampleModule extends AbstractModule {
  @Override
  protected void init() {
    this.name = "sample-module";
  }

  @Override
  protected void doWork() throws Exception {
    do {
      System.out.println(this.name + ": do nothing!");
      Thread.sleep(1000);
    } while (true);
  }
}
