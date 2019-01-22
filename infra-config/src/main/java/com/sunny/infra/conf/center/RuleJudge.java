package com.sunny.infra.conf.center;

import com.sunny.commom.utils.log.Log;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Created by sunnnychan@outlook.com on 2019/1/22.
 */
public class RuleJudge {

  private Boolean judge(String sql, Set<RuleModel> ruleModels) {
    for (RuleModel ruleModel : ruleModels) {
      if (ruleModel.getType().equals("re-mismatch")) {
        Log.info("RuleJudge by re-mismatch, config : %s", ruleModel.getContent());
        if (this.reMatch(sql, ruleModel.getContent())) {
          Log.info("re-mismatch check result is false");
          return false;
        }
      } else if (ruleModel.getType().equals("re-match")) {
        Log.info("RuleJudge by re-match, config : %s", ruleModel.getContent());
        if (!this.reMatch(sql, ruleModel.getContent())) {
          Log.info("re-match check result is false");
          return false;
        }
      } else {
        Log.warn("undefine rule type, will not handle");
      }
    }
    Log.info("all rule check is true");
    return true;
  }

  private Boolean reMatch(String str, String re) {
    Pattern pattern = Pattern.compile(re, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    Matcher pathMatcher = pattern.matcher(str);
    return pathMatcher.matches();
  }
}
