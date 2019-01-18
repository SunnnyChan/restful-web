package com.sunny.rw.server.domain.page;

import com.alibaba.fastjson.JSON;
import com.sunny.commom.utils.Log;
import jersey.repackaged.com.google.common.collect.Maps;

import java.util.HashMap;

/*
 * Created by sunnnychan@outlook.com on 2018/4/10.
 */

public abstract class PageBase<T> {
  protected HashMap<String, Object> data = Maps.newHashMap();
  protected T request;

  public final HashMap<String, Object> execute(T request) throws Exception {
    Log.info("request : %s", JSON.toJSONString(request));
    this.request = request;
    return this.executeWork();
  }

  public final HashMap<String, Object> execute() throws Exception {
    return this.executeWork();
  }

  public final HashMap<String, Object> executeWork() throws Exception {
    try {
      this.init();
      this.checkInput();
      this.importCache();
      this.doWork();
      this.exportCache();
      this.output();
      Log.info(String.format("return data : %s", JSON.toJSONString(this.data)));
      return this.data;
    } catch (Exception ex) {
      Log.warn("error occur, errorMessage : %s ", ex.getMessage());
      throw ex;
    } finally {
      this.doFinalWork();
    }
  }

  //初始化
  protected boolean init() throws Exception {
    Log.info("work flow init");
    return true;
  }

  //检查输入
  protected void checkInput() throws Exception {
    Log.info("work flow checkAuth");
  }

  //读取缓存
  protected void importCache() throws Exception {
    Log.info("work flow importCache");
  }

  //业务处理
  protected void doWork() throws Exception {
    Log.info("work flow doWork");
  }

  //写缓存
  protected void exportCache() throws Exception {
    Log.info("work flow exportCache");
  }

  //业务层输出
  protected void output() throws Exception {
    Log.info("work flow output");
  }

  //最后的处理工作
  protected void doFinalWork() {
    Log.info("work flow doFinalWork");
  }
}
