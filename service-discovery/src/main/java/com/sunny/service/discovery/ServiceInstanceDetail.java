package com.sunny.service.discovery;

import com.fasterxml.jackson.annotation.JsonRootName;

/*
 * Created by sunnnychan@outlook.com on 2019/1/18.
 */
@JsonRootName("details")
public class ServiceInstanceDetail {
  private String id;
  private String name;
  private String description;

  /**
   * constructor.
   * @param id service id
   * @param name service name
   */
  public ServiceInstanceDetail(String id, String name) {
    this(id, name, "");
  }

  /**
   * constructor.
   * @param id  id
   * @param name name
   * @param description desc
   */
  public ServiceInstanceDetail(String id, String name, String description) {
    this.id = id;
    this.name = name;
    this.description = description;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
