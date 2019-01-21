package com.sunny.commom.utils.json;

import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import java.util.Set;

/*
 * Created by sunnnychan@outlook.com on 2019/1/21.
 */
public class FastJsonFilter {
  /**
   * when print logs, call this method to get a FastJson filter.
   */
  public static SimplePropertyPreFilter create(Set<String> filterStrings) {
    SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
    filterStrings.forEach(r -> filter.getExcludes().add(r));
    return filter;
  }
}
