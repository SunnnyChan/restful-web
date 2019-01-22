package com.sunny.infra.conf.center.db;

import com.sunny.infra.conf.center.RuleModel;

import java.util.List;

/*
 * Created by sunnnychan@outlook.com on 2019/1/22.
 */
public interface RuleMapper {
  public List<RuleModel> getAllRules();
}
