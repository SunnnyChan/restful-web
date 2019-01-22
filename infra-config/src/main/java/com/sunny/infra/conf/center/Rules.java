package com.sunny.infra.conf.center;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/*
 * Created by sunnnychan@outlook.com on 2019/1/22.
 */
public enum Rules {
  INSTANCE;
  private List<RuleModel> rules;

  public List<RuleModel> getRules() {
    return rules;
  }

  public void setRules(List<RuleModel> rules) {
    this.rules = rules;
  }
}
