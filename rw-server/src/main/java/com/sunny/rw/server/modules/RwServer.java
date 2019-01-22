package com.sunny.rw.server.modules;

import com.sunny.commom.utils.module.AbstractModule;
import com.sunny.commom.web.jersey.JerseyServer;
import com.sunny.infra.conf.parser.ConfInit;
import com.sunny.rw.server.conf.ServerConf;
import org.eclipse.jetty.server.Server;

/*
 * Created by sunnnychan@outlook.com on 2019/1/22.
 */
public class RwServer {

  public static void main(String[] args) throws Exception {
    parserConf(args);
    RwServer rwServer = new RwServer();
    rwServer.startServer();
    rwServer.startModules();
  }

  private static void parserConf(String[] args) {
    if (args.length != 1) {
      System.out.println("Usage: <config file path>");
      System.exit(1);
    }
    ConfInit.init(args[0]);
  }

  private void startServer() throws Exception {
    Server server = JerseyServer.create(ServerConf.getServerPort(), "com.sunny.rw.server.controller");
    server.start();
  }

  private void startModules() throws Exception {
    if (!ServerConf.getModules().isEmpty()) {
      for (String moduleClassName : ServerConf.getModules()) {
        Class classType = Class.forName("com.sunny.rw.server.modules." + moduleClassName);
        AbstractModule module = (AbstractModule) classType.newInstance();
        module.start();
      }
    }
  }
}

