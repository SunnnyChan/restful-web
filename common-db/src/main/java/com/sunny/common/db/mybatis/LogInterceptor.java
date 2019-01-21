package com.sunny.common.db.mybatis;

import com.sunny.commom.utils.log.Log;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import sun.plugin2.main.server.ResultHandler;

import java.text.DateFormat;
import java.util.*;

@Intercepts({
    @Signature(type = Executor.class, method = "update",
        args = {MappedStatement.class, Object.class}),
    @Signature(type = Executor.class, method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})

/*
 * Created by sunnnychan@outlook.com on 2019/1/21.
 */
public class LogInterceptor implements Interceptor {
  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    HashMap<String, Object> logMessage = new HashMap<String, Object>();

    MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
    Object parameter = null;
    if (invocation.getArgs().length > 1) {
      parameter = invocation.getArgs()[1];
    }
    final String sqlId = mappedStatement.getId();
    BoundSql boundSql = mappedStatement.getBoundSql(parameter);
    Configuration configuration = mappedStatement.getConfiguration();
    Object returnValue;
    final long start = System.currentTimeMillis();
    returnValue = invocation.proceed();
    final long end = System.currentTimeMillis();
    String sqlStatement;
    try {
      sqlStatement = showSql(configuration, boundSql);
    } catch (Exception ex) {
      Log.error(String.format("get sql statement error, errorMessage : %s", ex.getMessage()));
      logMessage.put("errMsg", ex.getMessage());
      Arrays.stream(ex.getStackTrace()).forEach(r -> Log.warn(r.toString()));
      sqlStatement = boundSql.getSql();
    }
    logMessage.put("proc_time", (end - start));
    logMessage.put("sql_id", sqlId);
    logMessage.put("sql", sqlStatement);
    Log.info(logMessage.toString());
    return returnValue;
  }

  private static String showSql(Configuration configuration, BoundSql boundSql) {
    Object parameterObject = boundSql.getParameterObject();
    List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
    String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
    if (parameterMappings.size() > 0 && parameterObject != null) {
      TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
      if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
        sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));
      } else {
        MetaObject metaObject = configuration.newMetaObject(parameterObject);
        for (ParameterMapping parameterMapping : parameterMappings) {
          String propertyName = parameterMapping.getProperty();
          if (metaObject.hasGetter(propertyName)) {
            Object obj = metaObject.getValue(propertyName);
            sql = sql.replaceFirst("\\?", getParameterValue(obj));
          } else if (boundSql.hasAdditionalParameter(propertyName)) {
            Object obj = boundSql.getAdditionalParameter(propertyName);
            sql = sql.replaceFirst("\\?", getParameterValue(obj));
          }
        }
      }
    }
    return sql;
  }

  private static String getParameterValue(Object obj) {
    StringBuilder str = new StringBuilder();
    if (obj instanceof String) {
      str.append("'");
      str.append(obj.toString());
      str.append("'");
    } else if (obj instanceof Date) {
      DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
      str.append("'");
      str.append(formatter.format(obj));
      str.append("'");
    } else if (obj != null) {
      str.append(obj.toString());
    } else {
      return "null";
    }
    return str.toString();
  }

  @Override
  public Object plugin(Object target) {
    return Plugin.wrap(target, this);
  }

  @Override
  public void setProperties(Properties properties) {
  }
}
