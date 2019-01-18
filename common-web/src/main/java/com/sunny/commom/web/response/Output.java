package com.sunny.commom.web.response;

/*
 * Created by sunnnychan@outlook.com on 2019/1/18.
 */

import com.alibaba.fastjson.JSON;
import com.sunny.commom.utils.Log;

import java.util.Arrays;
import java.util.HashMap;

public class Output {
  public static JsonResponse okOutput(HashMap<String, Object> data) {
    JsonResponse response = successResponse(data);
    Log.info("OUTPUT : %s", JSON.toJSONString(response));
    return response;
  }

  public static JsonResponse errOutput(Exception ex) {
    Log.error("exception type : " + ex.getClass() + ", error message : " + ex.getMessage(), ex);
    Arrays.stream(ex.getStackTrace()).forEach(r -> Log.warn(r.toString()));
    int errorCode = ErrorDef.ERR_SYSTEM_ERR;
    if (ex instanceof ErrorCodeException) {
      errorCode = ((ErrorCodeException) ex).getErrCode();
    }
    JsonResponse response = errorResponse(errorCode, ex.getClass().getSimpleName(), ex.getMessage());
    Log.info("OUTPUT : %s", JSON.toJSONString(response));
    return response;
  }

  private static JsonResponse successResponse(HashMap<String, Object> data) {
    JsonResponse response = new JsonResponse();

    Meta meta = new Meta();
    meta.setCode(0);
    response.setMeta(meta);
    response.setData(data);
    return response;
  }

  private static JsonResponse errorResponse(int code, String errorType, String errorMsg) {
    Meta meta = new Meta();
    meta.setCode(code);
    meta.setErrorType(errorType);
    meta.setErrorMsg(errorMsg);

    JsonResponse response = new JsonResponse();
    response.setMeta(meta);

    return response;
  }
}
