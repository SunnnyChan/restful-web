package com.sunny.rw.server.conf;

import com.sunny.infra.conf.parser.ConfInit;
import com.typesafe.config.Config;

import java.util.Objects;

/**
 *  @author Created by sunnnychan@outlook.com on 2019/1/22.
 *
 *  基础设施相关配置
 */

public class InfraConf {
  private static Config infraConf;

  private static Config getConf() {
    if (Objects.isNull(infraConf)) {
      infraConf = ConfInit.getConfig().getConfig("infra");
    }
    return infraConf;
  }

  public static String getZkConnStr() {
    return getConf().getString("zk_conn-str");
  }

  public static String getZkServiceRegisterPath() {
    return getConf().getString("zk_register-path");
  }
}
