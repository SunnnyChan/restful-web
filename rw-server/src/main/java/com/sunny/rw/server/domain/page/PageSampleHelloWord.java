package com.sunny.rw.server.domain.page;

/*
 * Created by sunnnychan@outlook.com on 2019/1/18.
 */

import com.sunny.commom.utils.Log;
import com.sunny.rw.server.model.NullRequest;

public class PageSampleHelloWord extends PageBase<NullRequest> {

  @Override
  protected void doWork() throws Exception {
    Log.info("work flow doWork");
    this.data.put("message", "hello world!");
  }

}
