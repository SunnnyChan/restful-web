package com.sunny.rw.server.domain.page;

import com.alibaba.fastjson.JSONObject;
import com.sunny.commom.utils.log.Log;

/**
 * @author Created by sunnnychan@outlook.com on 2019/1/18.
 */

public class PageSampleEcho extends AbstractPageBase<JSONObject> {
  @Override
  protected void doWork() throws Exception {
    Log.info("work flow doWork");
    this.data.put("echo", this.request);
  }

}
