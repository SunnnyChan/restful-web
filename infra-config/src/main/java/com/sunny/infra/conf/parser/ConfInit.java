package com.sunny.infra.conf.parser;

/*
 * Created by sunnnychan@outlook.com on 2019/1/11.
 */

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.File;

public class ConfInit {
  private static Config config;

  public static void init(String configPath) {
    config = ConfigFactory.parseFile(new File(configPath));
  }

  public static Config getConfig() {
    return config;
  }
}
