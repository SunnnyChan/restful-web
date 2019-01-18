package com.sunny.rw.server.domain.page;

/*
 * Created by sunnnychan@outlook.com on 2019/1/18.
 */

import com.alibaba.fastjson.JSONObject;
import com.sunny.commom.utils.Log;

public class PageSampleEcho extends PageBase<JSONObject> {
  @Override
  protected void doWork() throws Exception {
    Log.info("work flow doWork");
    this.data.put("echo", this.request);
  }

}
