package com.sunny.commom.utils.log;

import com.alibaba.ttl.TransmittableThreadLocal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.HashMap;
import java.util.Objects;

/*
 * Created by sunnnychan@outlook.com on 2019/1/11.
 */
public class Log {
  // 如果封装当前库，传入封装的层级
  private static Integer bizCallLayer = 0;
  // 控制日志打印的长度
  private static Integer logMessageLength = 5000;
  // 日志通用信息
  private static TransmittableThreadLocal<HashMap<String, String>> logCommonMsg
      = new TransmittableThreadLocal<HashMap<String, String>>();
  // 业务扩展日志通用信息
  private static TransmittableThreadLocal<String> extendLogCommonMsg
      = new TransmittableThreadLocal<>();
  // 日志文件名
  private static TransmittableThreadLocal<String> mdcLogfile
      = new TransmittableThreadLocal<>();

  // init log Class
  public static void init(Integer bizCallLayer, Integer logMessageLength, String extendLogMsg) {
    Log.logCommonMsg.set(new HashMap<>());
    if (!Objects.isNull(bizCallLayer)) {
      Log.bizCallLayer = bizCallLayer;
    }
    Log.logMessageLength = logMessageLength;
    Log.initLogId();
    Log.logCommonMsg.get().put("startTime", String.valueOf(System.currentTimeMillis()));
    Log.extendLogCommonMsg.set(Objects.isNull(extendLogMsg) ? "" : extendLogMsg);
  }

  public static void init(Integer bizCallLayer, Integer logMessageLength,
                          String extendLogMsg, String mdcLogFileName) {
    Log.init(bizCallLayer, logMessageLength, extendLogMsg);
    Log.mdcLogfile.set(mdcLogFileName);
  }

  // get log Id
  private static void initLogId() {
    StringBuilder logId = new StringBuilder(20);
    String str = String.valueOf(System.nanoTime() + (int) (Math.random() * 5000));
    logId.append(str);
    str = logId.reverse().substring(str.length() - 9, str.length());
    Log.logCommonMsg.get().put("logId", str);
  }

  // org.slf4j.Logger object cache key (use Class Name)
  private static String getLoggerName() {
    StackTraceElement stack = Thread.currentThread().getStackTrace()[3 + bizCallLayer];
    return stack.getClassName();
  }

  // log common message
  private static String getCommonMsg() {
    StringBuilder logMsgBuilder = new StringBuilder(300);
    if (!Objects.isNull(logCommonMsg.get())) {
      logMsgBuilder.append("[");
      logMsgBuilder.append(Log.logCommonMsg.get().get("logId"));
      logMsgBuilder.append("]");

      logMsgBuilder.append(" [");
      logMsgBuilder.append(String.valueOf(System.currentTimeMillis()
          - Long.parseLong(Log.logCommonMsg.get().get("startTime"))));
      logMsgBuilder.append("ms]");
      logMsgBuilder.append(" ");
    }
    StackTraceElement stack = Thread.currentThread().getStackTrace()[4 + bizCallLayer];
    logMsgBuilder.append(stack.getFileName());
    logMsgBuilder.append(":");
    logMsgBuilder.append(stack.getLineNumber());
    return logMsgBuilder.toString();
  }

  // log last output message
  private static String getLogMessage(String logMsg) {
    StringBuilder logMsgBuilder = new StringBuilder(500);
    logMsgBuilder.append(Log.getCommonMsg());
    logMsgBuilder.append(" [");
    logMsgBuilder.append(Thread.currentThread().getName());
    logMsgBuilder.append("] ");
    if (!logMsg.contains("traceId")) {
      logMsgBuilder.append(Log.extendLogCommonMsg.get());
      logMsgBuilder.append("||_msg=");
    }
    String logMessage = logMsgBuilder.append(logMsg).toString();
    return (!Objects.isNull(Log.logMessageLength) && Log.logMessageLength > 0
        && logMessage.length() > Log.logMessageLength)
        ? logMessage.substring(0, Log.logMessageLength - 1)
        + " ... ... OMITTED HERE MANY WORDS ... ..." : logMessage;
  }

  // log output
  public static void trace(String msg) {
    Logger logger = LoggerFactory.getLogger(Log.getLoggerName());
    if (!Objects.isNull(Log.mdcLogfile.get())) {
      MDC.put("logFileName", Log.mdcLogfile.get());
      logger.trace(Log.getLogMessage(msg));
      MDC.remove("logFileName");
    } else {
      logger.trace(Log.getLogMessage(msg));
    }
  }

  public static void trace(String format, Object... value) {
    Logger logger = LoggerFactory.getLogger(Log.getLoggerName());
    if (!Objects.isNull(Log.mdcLogfile.get())) {
      MDC.put("logFileName", Log.mdcLogfile.get());
      logger.trace(Log.getLogMessage(String.format(format, value)));
      MDC.remove("logFileName");
    } else {
      logger.trace(Log.getLogMessage(String.format(format, value)));
    }
  }

  public static void debug(String msg) {
    Logger logger = LoggerFactory.getLogger(Log.getLoggerName());
    if (!Objects.isNull(Log.mdcLogfile.get())) {
      MDC.put("logFileName", Log.mdcLogfile.get());
      logger.debug(Log.getLogMessage(msg));
      MDC.remove("logFileName");
    } else {
      logger.debug(Log.getLogMessage(msg));
    }
  }

  public static void debug(String format, Object... value) {
    Logger logger = LoggerFactory.getLogger(Log.getLoggerName());
    if (!Objects.isNull(Log.mdcLogfile.get())) {
      MDC.put("logFileName", Log.mdcLogfile.get());
      logger.debug(Log.getLogMessage(String.format(format, value)));
      MDC.remove("logFileName");
    } else {
      logger.debug(Log.getLogMessage(String.format(format, value)));
    }
  }

  public static void info(String msg) {
    Logger logger = LoggerFactory.getLogger(Log.getLoggerName());
    if (!Objects.isNull(Log.mdcLogfile.get())) {
      MDC.put("logFileName", Log.mdcLogfile.get());
      logger.info(Log.getLogMessage(msg));
      MDC.remove("logFileName");
    } else {
      logger.info(Log.getLogMessage(msg));
    }
  }

  public static void info(String format, Object... value) {
    if (!Objects.isNull(Log.mdcLogfile.get())) {
      String logFileName = Log.mdcLogfile.get();
      MDC.put("logFileName", logFileName);
      Logger logger = LoggerFactory.getLogger(Log.getLoggerName());
      logger.info(Log.getLogMessage(String.format(format, value)));
      MDC.remove("logFileName");
    } else {
      Logger logger = LoggerFactory.getLogger(Log.getLoggerName());
      logger.info(Log.getLogMessage(String.format(format, value)));
    }
  }

  public static void warn(String msg) {
    Logger logger = LoggerFactory.getLogger(Log.getLoggerName());
    if (!Objects.isNull(Log.mdcLogfile.get())) {
      MDC.put("logFileName", Log.mdcLogfile.get());
      logger.warn(Log.getLogMessage(msg));
      MDC.remove("logFileName");
    } else {
      logger.warn(Log.getLogMessage(msg));
    }
  }

  public static void warn(String format, Object... value) {
    Logger logger = LoggerFactory.getLogger(Log.getLoggerName());
    if (!Objects.isNull(Log.mdcLogfile.get())) {
      MDC.put("logFileName", Log.mdcLogfile.get());
      logger.warn(Log.getLogMessage(String.format(format, value)));
      MDC.remove("logFileName");
    } else {
      logger.warn(Log.getLogMessage(String.format(format, value)));
    }
  }

  public static void error(String msg) {
    Logger logger = LoggerFactory.getLogger(Log.getLoggerName());
    if (!Objects.isNull(Log.mdcLogfile.get())) {
      MDC.put("logFileName", Log.mdcLogfile.get());
      logger.error(Log.getLogMessage(msg));
      MDC.remove("logFileName");
    } else {
      logger.error(Log.getLogMessage(msg));
    }
  }

  public static void error(String format, Object... value) {
    Logger logger = LoggerFactory.getLogger(Log.getLoggerName());
    if (!Objects.isNull(Log.mdcLogfile.get())) {
      MDC.put("logFileName", Log.mdcLogfile.get());
      logger.error(Log.getLogMessage(String.format(format, value)));
      MDC.remove("logFileName");
    } else {
      logger.error(Log.getLogMessage(String.format(format, value)));
    }
  }

  public static void error(String msg, Throwable throwable) {
    Logger logger = LoggerFactory.getLogger(Log.getLoggerName());
    if (!Objects.isNull(Log.mdcLogfile.get())) {
      MDC.put("logFileName", Log.mdcLogfile.get());
      logger.error(Log.getLogMessage(msg), throwable);
      MDC.remove("logFileName");
    } else {
      logger.error(Log.getLogMessage(msg), throwable);
    }
  }
}
