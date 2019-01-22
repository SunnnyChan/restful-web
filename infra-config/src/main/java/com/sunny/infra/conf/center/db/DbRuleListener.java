package com.sunny.infra.conf.center.db;

import com.alibaba.fastjson.JSON;
import com.sunny.commom.utils.dp.SingletonFactory;
import com.sunny.commom.utils.log.Log;
import com.sunny.commom.utils.log.LogInit;
import com.sunny.commom.utils.module.AbstractModule;
import com.sunny.infra.conf.center.RuleModel;
import com.sunny.infra.conf.center.Rules;

import java.util.Arrays;
import java.util.List;

/*
 * Created by sunnnychan@outlook.com on 2019/1/22.
 */
public class DbRuleListener extends AbstractModule {
  RuleService ruleService = SingletonFactory.get(RuleService.class);

  @Override
  public synchronized void start() {
    this.sleepInterval = 60000;
    this.name = "dynamic-config";
    super.start();
  }

  protected void doWork() {
    try {
      do {
        LogInit.initLog("dynamic-config");
        Log.info("dynamic-config start update");
        List<RuleModel> ruleModels = this.ruleService.getAllRules();
        Log.info("get enable configs from db : %s", JSON.toJSONString(ruleModels));
        Rules.INSTANCE.setRules(ruleModels);
        Log.info("dynamic-config end update");
      } while (false);
    } catch (Exception e) {
      Log.error("Database rule listen error! error : %s", e.getMessage());
      Arrays.stream(e.getStackTrace()).forEach(r -> Log.warn(r.toString()));
    }
  }
}
