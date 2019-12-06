package com.love.utils;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** @Author: Lee CE @Description: @Date: 2018/7/13 10:31 @Modified: */
public class JsonUtils {
  /**
   * Bean对象转JSON
   *
   * @param object
   * @param dataFormatString
   * @return
   */
  public static String beanToJson(Object object, String dataFormatString) {
    if (object != null) {
      if (StringUtils.isEmpty(dataFormatString)) {
        return JSONObject.toJSONString(object);
      }
      return JSON.toJSONStringWithDateFormat(object, dataFormatString);
    } else {
      return null;
    }
  }

  /**
   * Bean对象转JSON
   *
   * @param object
   * @return
   */
  public static String beanToJson(Object object) {
    if (object != null) {
      return JSON.toJSONString(object);
    } else {
      return null;
    }
  }

  /**
   * String转JSON字符串
   *
   * @param key
   * @param value
   * @return
   */
  public static String stringToJsonByFastjson(String key, String value) {
    if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
      return null;
    }
    Map<String, String> map = new HashMap<String, String>();
    map.put(key, value);
    return beanToJson(map, null);
  }

  /**
   * 将json字符串转换成对象
   *
   * @param json
   * @param clazz
   * @return
   */
  public static Object jsonToBean(String json, Object clazz) {
    if (StringUtils.isEmpty(json) || clazz == null) {
      return null;
    }
    return JSON.parseObject(json, clazz.getClass());
  }

  public static <T> T jsonToBean(String json, Class<T> clazz) {
    if (StringUtils.isEmpty(json) || clazz == null) {
      return null;
    }
    return JSON.parseObject(json, clazz);
  }

  /**
   * json字符串转map
   *
   * @param json
   * @return
   */
  public static Map<String, Object> jsonToMap(String json) {
    if (StringUtils.isEmpty(json)) {
      return null;
    }
    return JSON.parseObject(json, Map.class);
  }

  /**
   * json字符串转list
   *
   * @param jsonStr
   * @return
   */
  public static <T> List<T> stringToList(String jsonStr, Class<T> model) {
    List<T> object = (List<T>) JSONArray.parseArray(jsonStr, model);
    return object;
  }
}
