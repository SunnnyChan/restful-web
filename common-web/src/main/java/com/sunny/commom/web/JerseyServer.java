package com.sunny.commom.web;

/*
 * Created by sunnnychan@outlook.com  on 2019/1/11.
 */

import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class JerseyServer {

  /**
   * get server.
   *
   * @return server
   */
  public static Server startServer(int port) {
    ResourceConfig rc = new ResourceConfig();
    URI baseUri = UriBuilder.fromUri("http://0.0.0.0/").port(port).build();
    return JettyHttpContainerFactory.createServer(baseUri, rc).getServer();
  }
}
