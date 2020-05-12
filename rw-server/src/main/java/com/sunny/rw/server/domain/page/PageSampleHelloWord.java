package com.sunny.rw.server.domain.page;

import com.sunny.commom.utils.log.Log;
import com.sunny.rw.server.model.NullRequest;

/**
 * @author Created by sunnnychan@outlook.com on 2019/1/18.
 */

public class PageSampleHelloWord extends AbstractPageBase<NullRequest> {

  @Override
  protected void doWork() throws Exception {
    Log.info("work flow doWork");
    this.data.put("message", "hello world!");
  }

}
