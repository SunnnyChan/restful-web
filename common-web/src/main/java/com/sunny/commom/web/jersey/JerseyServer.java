package com.sunny.commom.web.jersey;

/*
 * Created by sunnnychan@outlook.com  on 2019/1/11.
 */

import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class JerseyServer {

  /**
   * jersey config.
   */
  public static class ServerConfig extends ResourceConfig {
    /**
     * get config.
     */
    public ServerConfig(String controllerPath) {
      packages(controllerPath);
      register(JacksonFeature.class);
    }
  }

  /**
   * get server.
   * @return server
   */
  public static Server create(int port, String controllerPath) {
    ServerConfig serverConfig = new ServerConfig(controllerPath);
    URI baseUri = UriBuilder.fromUri("http://0.0.0.0/").port(port).build();
    return JettyHttpContainerFactory.createServer(baseUri, serverConfig).getServer();
  }
}
