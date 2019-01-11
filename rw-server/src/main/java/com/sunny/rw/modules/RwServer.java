package com.sunny.rw.modules;

/*
 * Created by sunnnychan@outlook.com on 2019/1/11.
 */

import com.sunny.commom.utils.conf.ConfInit;
import com.sunny.commom.web.JerseyServer;
import com.sunny.rw.conf.Conf;
import org.eclipse.jetty.server.Server;

public class RwServer {

  public static void main(String[] args) throws Exception {
    if (args.length != 1) {
      System.out.println("Usage: <config file path>");
      System.exit(1);
    }
    ConfInit.init(args[0]);
    Server server = JerseyServer.startServer(Conf.getServerPort());
    server.start();

    ModuleInterface sampleModule = new SampleModule();
    sampleModule.start();
  }
}
