package com.sunny.rw.server.conf;

import com.sunny.infra.conf.parser.ConfInit;
import com.typesafe.config.Config;

import java.util.Objects;

/**
 * @author Created by sunnnychan@outlook.com  on 2019/1/22.
 * 依赖的服务相关配置
 */
public class RpcServiceConf {
  private static Config rpcServiceConf;

  private static Config getConf() {
    if (Objects.isNull(rpcServiceConf)) {
      rpcServiceConf = ConfInit.getConfig().getConfig("infra");
    }
    return rpcServiceConf;
  }

}
