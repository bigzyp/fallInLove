package com.love.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: lixin
 * @Description: 日志工具类
 * @Date: 2018/11/2 14:17
 */
public class LogUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger("error");
    private static final Logger FATAL_LOGGER = LoggerFactory.getLogger("fatal");

    /**
       * @Description: 记录debug日志
       * @params:  [messages]
       * @Return:  void
       * @Author:  lixin
       * @Date:  2018/11/2 14:19
       * @Modified:
       */
    public static void debug(String... messages) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(mergeStrings(messages));
        }
    }



    /**
       * @Description: 记录Info日志
       * @params:  [messages]
       * @Return:  void
       * @Author:  lixin
       * @Date:  2018/11/2 14:19
       * @Modified:
       */
    public static void info(String... messages) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(mergeObjects(messages));
        }
    }

    /**
       * @Description: 记录warn日志
       * @params:  [messages]
       * @Return:  void
       * @Author:  lixin
       * @Date:  2018/11/2 14:19
       * @Modified:
       */
    public static void warn(String... messages) {
        if (LOGGER.isWarnEnabled()) {
            LOGGER.warn(mergeObjects(messages));
        }
    }

    /**
       * @Description: 记录错误日志
       * @params:  [messages]
       * @Return:  void
       * @Author:  lixin
       * @Date:  2018/11/2 14:23
       * @Modified:
       */
    public static void error(String... messages) {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error(mergeStrings(messages));
        }

    }

    /**
       * @Description: 记录错误日志
       * @params:  [e, messages]
       * @Return:  void
       * @Author:  lixin
       * @Date:  2018/11/2 14:23
       * @Modified:
       */
    public static void error(Throwable e, String... messages) {
        if (LOGGER.isErrorEnabled()) {
            if (e == null) {
                LOGGER.error(mergeStrings(messages));
            } else {
                LOGGER.error(mergeStrings(messages), e);
            }
        }
    }

    /**
     * @description: 记录fatal级别日志
     * @param messages
     * @return void
     * @author zmc
     * @date 2019-06-11 11:29
     */
    public static void fatal(String... messages) {
        if (FATAL_LOGGER.isErrorEnabled()) {
            FATAL_LOGGER.error(mergeStrings(messages));
        }
    }

    /**
     * @description: 记录fatal级别日志
     * @param e
     * @param messages
     * @return void
     * @author zmc
     * @date 2019-06-11 11:25
     */
    public static void fatal(Throwable e, String... messages) {
        if (FATAL_LOGGER.isErrorEnabled()) {
            if (e == null) {
                FATAL_LOGGER.error(mergeStrings(messages));
            } else {
                FATAL_LOGGER.error(mergeStrings(messages), e);
            }
        }
    }

    /**
       * @Description: 将string数组合并为一个字符串返回
       * @params:  [strings]
       * @Return:  java.lang.String
       * @Author:  lixin
       * @Date:  2018/11/2 14:21
       * @Modified:
       */
    private static String mergeStrings(String[] strings) {
        if (strings == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : strings) {
            stringBuilder.append(string);
        }
        return stringBuilder.toString();
    }

    /**
       * @Description: 将string数组合并为一个字符串返回
       * @params:  [objects]
       * @Return:  java.lang.String
       * @Author:  lixin
       * @Date:  2018/11/2 14:21
       * @Modified:
       */
    private static String mergeObjects(Object[] objects) {
        if (objects == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Object obj : objects) {
            if (obj == null) {
                continue;
            }
            if (obj instanceof String) {
                stringBuilder.append(obj);
            } else {
                stringBuilder.append(obj.toString());
            }
        }
        return stringBuilder.toString();
    }

}
