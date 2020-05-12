package com.sunny.rw.server.application;

import com.sunny.commom.utils.module.AbstractModule;
import com.sunny.commom.web.jersey.JerseyServer;
import com.sunny.rw.server.conf.ServerConf;
import org.eclipse.jetty.server.Server;

/**
 * @author Created by sunnnychan@outlook.com on 2019/1/22.
 */
public abstract class AbstractRwServer {

  protected void startServer() throws Exception {
    Server server = JerseyServer.create(ServerConf.getServerPort(), ServerConf.getControllerClassPath());
    server.start();
  }

  protected void startModules() throws Exception {
    if (!ServerConf.getModules().isEmpty()) {
      for (String moduleClassName : ServerConf.getModules()) {
        Class classType = Class.forName(ServerConf.getModulesClassPath() + "." + moduleClassName);
        AbstractModule module = (AbstractModule) classType.newInstance();
        module.start();
      }
    }
  }
}

