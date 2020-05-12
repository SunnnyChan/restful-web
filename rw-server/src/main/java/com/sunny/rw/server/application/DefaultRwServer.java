package com.sunny.rw.server.application;

import com.sunny.infra.conf.parser.ConfInit;

/**
 * @author Created by sunnnychan@outlook.com on 2019/1/22.
 */
public class DefaultRwServer extends AbstractRwServer {

  public static void main(String[] args) throws Exception {
    parserConf(args);
    DefaultRwServer rwServer = new DefaultRwServer();
    rwServer.startServer();
    rwServer.startModules();
  }

  protected static void parserConf(String[] args) {
    if (args.length != 1) {
      System.out.println("Usage: <config file path>");
      System.exit(1);
    }
    ConfInit.init(args[0]);
  }
}

