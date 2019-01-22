package com.sunny.infra.conf.center.db;

import com.sunny.commom.utils.resource.Resource;
import com.sunny.infra.conf.center.RuleModel;
import com.sunny.infra.conf.utils.MysqlSessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/*
 * Created by sunnnychan@outlook.com on 2019/1/22.
 */
public class RuleService {
  private SqlSessionFactory sessionFactory;

  public RuleService() {
    this.sessionFactory = MysqlSessionFactory.get();
  }

  public List<RuleModel> getAllRules() {
    try (SqlSession sqlSession = sessionFactory.openSession(true)) {
      RuleMapper RuleMapper = sqlSession.getMapper(RuleMapper.class);
      return RuleMapper.getAllRules();
    }
  }
}
