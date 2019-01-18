package com.sunny.commom.web.response;

/*
 * Created by sunnnychan@outlook.com on 2019/1/18.
 */

public class ErrorCodeException extends Exception {
  protected int errCode;

  public ErrorCodeException(int errCode, String errMsg) {
    super(errMsg);
    this.errCode = errCode;
  }

  public Integer getErrCode() {
    return this.errCode;
  }

}
