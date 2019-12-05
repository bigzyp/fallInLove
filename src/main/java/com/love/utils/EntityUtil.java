package com.love.utils;


import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: lixin
 * @Description: 实体类工具
 * @Date: 2018/8/10 11:08
 */
public class EntityUtil {


    /**
       * @Description: 给对象的属性为null的设置默认值
       * @params:  [entity]
       * @Return:  void
       * @Author:  lixin
       * @Date:  2018/8/10 11:41
       * @Modified:
       */
    public static void entityInit(Object entity) throws IllegalAccessException {
        //得到类对象
        Class clazz = entity.getClass();

        //得到类中所有属性
        Field[] fs = clazz.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            //设置这此属性可以访问
            f.setAccessible(true);
            //得到此属性的值
            Object val = null;
                val = f.get(entity);
                if (val == null){
                    //获取类型名称简写
                    String type = f.getType().getSimpleName();
                    if (type.equals("String")) {
                        f.set(entity,"");
                    } else if (type.equals("Integer")){
                        f.set(entity,new Integer(0));
                    } else if (type.equals("Long")) {
                        f.set(entity,new Long(0));
                    } else if (type.equals("Char")){
                        f.set(entity,"");
                    } else if (type.equals("Date")) {
                        f.set(entity,new Date());
                    } else if (type.equals("BigDecimal")) {
                        f.set(entity,new BigDecimal(0));
                    } else if (type.equals("Double")) {
                        f.set(entity,new Double(0));
                    } else if (type.equals("Short")) {
                        f.set(entity,new Short("0"));
                    }
                }
        }

    }

}
