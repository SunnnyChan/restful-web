package com.sunny.commom.web.response;

/*
 * Created by sunnnychan@outlook.com on 2019/1/11.
 */

import java.util.HashMap;

public class JsonResponse {
  private Meta meta;
  private HashMap<String, Object> data;


  public Meta getMeta() {
    return meta;
  }

  public void setMeta(Meta meta) {
    this.meta = meta;
  }

  public HashMap<String, Object> getData() {
    return data;
  }

  public void setData(HashMap<String, Object> data) {
    this.data = data;
  }
}
