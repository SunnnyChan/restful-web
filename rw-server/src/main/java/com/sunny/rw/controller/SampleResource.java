package com.sunny.rw.controller;

import com.sunny.commom.web.response.JsonResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/*
 * Created by sunnnychan@outlook.com on 2019/1/11.
 */
@Path("rw/v1/sample/hello-world")
public class SampleResource {

  @GET
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public JsonResponse getSqlCache() {
    JsonResponse response = new JsonResponse();
    response.getMeta().setCode(0);
    response.getData().put("data", "hello world!");
    return response;
  }
}
