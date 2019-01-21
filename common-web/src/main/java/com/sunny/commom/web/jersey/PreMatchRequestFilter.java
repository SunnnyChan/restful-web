package com.sunny.commom.web.jersey;

/*
 * Created by sunnnychan@outlook.com on 2019/1/21.
 */

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import java.io.IOException;

public class PreMatchRequestFilter implements ContainerRequestFilter {
  private ContainerRequestContext requestContext;

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {
    this.requestContext = requestContext;
    this.initLog();
    this.printRequestLog();
  }

  private void initLog() {

  }

  private void printRequestLog() {

  }
}
