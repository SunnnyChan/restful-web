package com.sunny.rw.server.conf;

/*
 * Created by sunnnychan@outlook.com on 2019/1/11.
 */

import com.sunny.infra.conf.parser.ConfInit;
import com.typesafe.config.Config;

import java.util.List;
import java.util.Objects;

public class ServerConf {
  private static Config serverConf;

  private static Config getServerConf() {
    if (Objects.isNull(serverConf)) {
      serverConf = ConfInit.getConfig().getConfig("server");
    }
    return serverConf;
  }

  public static String getServerName() {
    return getServerConf().getString("name");
  }

  public static List<String> getModules() {
    return getServerConf().getStringList("modules");
  }

  // 当前配置的部署环境类型（本地，线上，生产），会读取不一样的配置。
  public static String getEnv() {
    return getServerConf().getString("env");
  }
  // 服务端口号
  public static int getServerPort() {
    return getServerConf().getInt("port");
  }
  // 是否需要注册服务
  public static boolean getServiceDiscoveryFlag() {
    return getServerConf().getBoolean("service-register-flag");
  }

}
