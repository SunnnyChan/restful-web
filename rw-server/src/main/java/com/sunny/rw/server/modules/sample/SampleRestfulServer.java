package com.sunny.rw.server.modules.sample;

/*
 * Created by sunnnychan@outlook.com on 2019/1/11.
 */

import com.sunny.commom.utils.conf.ConfInit;
import com.sunny.commom.web.jersey.JerseyServer;
import com.sunny.rw.server.conf.Conf;
import com.sunny.rw.server.modules.ModuleInterface;
import org.eclipse.jetty.server.Server;

public class SampleRestfulServer {

  public static void main(String[] args) throws Exception {
    if (args.length != 1) {
      System.out.println("Usage: <config file path>");
      System.exit(1);
    }
    ConfInit.init(args[0]);

    Server server = JerseyServer.create(Conf.getServerPort(), "com.sunny.rw.server.controller");
    server.start();

    ModuleInterface sampleModule = new SampleModule();
    sampleModule.start();
  }
}
