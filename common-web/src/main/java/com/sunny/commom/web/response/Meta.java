package com.sunny.commom.web.response;

/*
 * Created by sunnnychan@outlook.com on 2019/1/11.
 */

public class Meta {
  private int code = -1;
  private String errorType;
  private String errorMsg;
  private String traceId;

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getErrorType() {
    return errorType;
  }

  public void setErrorType(String errorType) {
    this.errorType = errorType;
  }

  public String getErrorMsg() {
    return errorMsg;
  }

  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }

  public String getTraceId() {
    return traceId;
  }

  public void setTraceId(String traceId) {
    this.traceId = traceId;
  }
}
