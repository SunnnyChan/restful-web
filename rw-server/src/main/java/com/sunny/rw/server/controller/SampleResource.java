package com.sunny.rw.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.sunny.commom.web.response.JsonResponse;
import com.sunny.commom.web.response.Output;
import com.sunny.rw.server.domain.page.PageSampleEcho;
import com.sunny.rw.server.domain.page.PageSampleHelloWord;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/*
 * Created by sunnnychan@outlook.com on 2019/1/11.
 */

@Path("/sample/v1")
public class SampleResource {

  @GET
  @Path("/hello-world")
  @Produces(MediaType.APPLICATION_JSON)
  public JsonResponse helloWorld() {
    try {
      return Output.okOutput(new PageSampleHelloWord().execute());
    } catch (Exception ex) {
      return Output.errOutput(ex);
    }
  }

  @POST
  @Path("/echo")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public JsonResponse echo(JSONObject request) {
    try {
      return Output.okOutput(new PageSampleEcho().execute(request));
    } catch (Exception ex) {
      return Output.errOutput(ex);
    }
  }

}
