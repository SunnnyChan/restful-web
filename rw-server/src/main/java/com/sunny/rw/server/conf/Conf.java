package com.sunny.rw.server.conf;

/*
 * Created by sunnnychan@outlook.com on 2019/1/11.
 */

import com.sunny.commom.utils.conf.ConfInit;
import com.typesafe.config.Config;

import java.util.Objects;

public class Conf {
  private static Config serverConf;

  private static Config getServerConf() {
    if (Objects.isNull(serverConf)) {
      serverConf = ConfInit.getConfig().getConfig("server");
    }
    return serverConf;
  }

  public static String getEnv() {
    return getServerConf().getString("env");
  }

  public static int getServerPort() {
    return getServerConf().getInt("port");
  }
}
