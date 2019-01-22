package com.sunny.commom.utils.log;

/*
 * Created by sunnnychan@outlook.com on 2019/1/22.
 */

public class LogInit {
  public static void initLog(String logFileName) {
    Log.init(null, 5000, "", logFileName);
  }
}
