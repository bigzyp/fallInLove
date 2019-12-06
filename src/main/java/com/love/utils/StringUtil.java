package com.love.utils;


import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Author: Lee CE
 * @Description:常用的字符串相关的工具类
 * @Date: 2018/7/12 15:19
 * @Modified:
 */
public final class StringUtil {

    /**
     * 判断是否是空字符串 null和"" 都返回 true
     *
     * @param str 判断的字符串
     * @return 是否有效
     */
    public  static boolean isEmpty(String str) {
        return str == null || str.equals("");
    }

    /**
     * 判定第一个字符串是否等于的第二个字符串中的某一个值
     *
     * @param str1 测试的字符串
     * @param str2 字符串数组(用,分割)
     * @return 是否包含
     */
    public  static boolean requals(String str1, String str2) {
        if (str1 != null && str2 != null) {
            str2 = str2.replaceAll("\\s*", "");
            String[] arr = str2.split(",");
            for (String t : arr) {
                if (t.equals(str1.trim())) {
                    return true;
                }
            }
        }
        return false;
    }




    /**
     * 获取字符串str在String中出现的次数
     *
     * @param string 处理的字符串
     * @param str 子字符串
     */
    public  static int countSubStr(String string, String str) {
        if ((str == null) || (str.length() == 0) || (string == null) || (string.length() == 0)) {
            return 0;
        }
        int count = 0;
        int index = 0;
        while ((index = string.indexOf(str, index)) != -1) {
            count++;
            index += str.length();
        }
        return count;
    }


    /**
     * 替换一个出现的子串
     *
     * @param s    source string
     * @param sub  substring to replace
     * @param with substring to replace with
     */
    public  static String replaceFirst(String s, String sub, String with) {
        int i = s.indexOf(sub);
        if (i == -1) {
            return s;
        }
        return s.substring(0, i) + with + s.substring(i + sub.length());
    }


    /**
     * 替换最后一次出现的字串
     * Replaces the very last occurrence of a substring with supplied string.
     *
     * @param s    source string
     * @param sub  substring to replace
     * @param with substring to replace with
     */
    public  static String replaceLast(String s, String sub, String with) {
        int i = s.lastIndexOf(sub);
        if (i == -1) {
            return s;
        }
        return s.substring(0, i) + with + s.substring(i + sub.length());
    }


    /**
     * 删除所有的子串
     * Removes all substring occurrences from the string.
     *
     * @param s   source string
     * @param sub substring to remove
     */
    public  static String remove(String s, String sub) {
        int c      = 0;
        int sublen = sub.length();
        if (sublen == 0) {
            return s;
        }
        int i = s.indexOf(sub, c);
        if (i == -1) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s.length());
        do {
            sb.append(s.substring(c, i));
            c = i + sublen;
        } while ((i = s.indexOf(sub, c)) != -1);
        if (c < s.length()) {
            sb.append(s.substring(c, s.length()));
        }
        return sb.toString();
    }

    /**
     * 将字符串首字母转大写
     * @param str 需要处理的字符串
     */
    public  static String upperFirstChar(String str){
        if(isEmpty(str)){
            return "";
        }
        char[] cs=str.toCharArray();
        if((cs[0] >= 'a') && (cs[0] <= 'z')){
            cs[0] -= (char) 0x20;
        }
        return String.valueOf(cs);
    }

    /**
     * 将字符串首字母转小写
     * @param str
     * @return
     */
    public  static String lowerFirstChar(String str){
        if(isEmpty(str)){
            return "";
        }
        char[] cs=str.toCharArray();
        if((cs[0] >= 'A') && (cs[0] <= 'Z')){
            cs[0] += (char) 0x20;
        }
        return String.valueOf(cs);
    }



    /**
       * @Description: 获取随机 6 位验证码
       * @params:  []
       * @Return:  java.lang.String
       * @Author:  sulijuan
       * @Date:  2018/9/4 10:51
       * @Modified:
       */
    public static String verifyCode(){
        //生成短信验证码
        return String.valueOf(new Random().nextInt(899999) + 100000);
    }


    /**
       * @Description: 将字符串按照splitor分割成的字符串数组转换成List</Integer>
       * @params:  [s, splitor]
       * @Return:  java.util.List<java.lang.Integer>
       * @Author:  huanghao
       * @Date:  2018/9/14 下午2:58
       * @Modified:
       */
    public static List<Integer> string2IntegerList(String s, String splitor){
        if (null == s || s.length() == 0){
            return null;
        }
        Pattern p = Pattern.compile(splitor);
        return p.splitAsStream(s).map(Integer::valueOf).collect(Collectors.toList());
    }

    /**
     * @Description: 将字符串按照splitor分割成的字符串数组转换成List</Integer>
     * @params:  [s, splitor]
     * @Return:  java.util.List<java.lang.Integer>
     * @Author:  huanghao
     * @Date:  2018/9/14 下午2:58
     * @Modified:
     */
    public static List<Long> stringToLong(String s, String splitor){
        if (null == s || s.length() == 0){
            return null;
        }
        Pattern p = Pattern.compile(splitor);
        List<Long> list = p.splitAsStream(s).map(Long::valueOf).collect(Collectors.toList());
        return list;
    }

    private static int getBeforeLength(int stringLength){
        if (stringLength <= 11){
            return 3;
        }
        return 4;
    }

    private static int getAfterLength(int stringLength){
        if (stringLength <= 4){
            return 0;
        }
        if (stringLength <= 7){
            return stringLength - 3;
        }
        if (stringLength <= 11){
            return 4;
        }
        return 4;
    }

    /**
       * @Description: URL 转码
       * @params:  [str]
       * @Return:  java.lang.String
       * @Author:  sulijuan
       * @Date:  2018/11/13 17:03
       * @Modified:
       */
    public static String getURLEncoderString(String str) {
        String result;
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LogUtil.error(e, " URLEncoder.encode error ！");
            return "";
        }
        return result;
    }

    /**
     * @Description: 验证是否是数字
     * @params:  [str]
     * @Return:  boolean
     * @Author:  sulijuan
     * @Date:  2018/11/6 12:49
     * @Modified:
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * @Description: 日期路径
     * @params: []
     * @Return: java.lang.String
     * @Author: ckx
     * @Date: 2018/7/30 9:37
     * @Modified:
     */
    public static String getDatePath() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return year + "_" + month + "/" + day + "/";
    }

    /**
       * @Description:  功能描述: 替换
       * @params:  [str, r, c]
       * @Return:  java.lang.String
       * @Author:  sulijuan
       * @Date:  2018/12/6 16:38
       * @Modified:
       */
    public static String replaceAll(String str, String r, String c) {
        str = str.replace(r, c).replace(r, c);
        if (str.indexOf(r) >= 0){
            return replaceAll(str, r, c);
        }
        return str;
    }

    /**
     * @Description: URL 转码
     * @params:  [str]
     * @Return:  java.lang.String
     * @Author:  sulijuan
     * @Date:  2018/11/13 17:03
     * @Modified:
     */
    public static String getURLDecoderString(String str) {
        String result;
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LogUtil.error(e, " URLEncoder.encode error ！");
            return "";
        }
        return result;
    }

    /**
     * 判断字符串是否为URL
     * @param urls 用户头像key
     * @return true:是URL、false:不是URL
     */
    public static boolean isHttpUrl(String urls) {
        boolean isurl = false;
        String regex = "(((https|http)?://)?([a-z0-9]+[.])|(www.))"
                + "\\w+[.|\\/]([a-z0-9]{0,})?[[.]([a-z0-9]{0,})]+((/[\\S&&[^,;\u4E00-\u9FA5]]+)+)?([.][a-z0-9]{0,}+|/?)";//设置正则表达式

        Pattern pat = Pattern.compile(regex.trim());//比对
        Matcher mat = pat.matcher(urls.trim());
        isurl = mat.matches();//判断是否匹配
        if (isurl) {
            isurl = true;
        }
        return isurl;
    }


    /**
       * @Description: 校验字符串是否是数字字母
       * @params:  [value]
       * @Return:  boolean
       * @Author:  sulijuan
       * @Date:  2019/8/19 13:34 
       * @Modified:
       */
    public static boolean checkValue(String value) {
        boolean check = false;
        String regex = "[0-9a-zA-Z]*";//设置正则表达式

        Pattern pat = Pattern.compile(regex.trim());//比对
        Matcher mat = pat.matcher(value.trim());
        check = mat.matches();//判断是否匹配
        if (check) {
            check = true;
        }
        return check;
    }

    /**
     * @description: null转空处理
     * @param obj
     * @return java.lang.String
     * @author zmc
     * @date 2019-06-10 10:47
     */
    public static String changeNullToEmpty(Object obj) {
        if(null == obj) {
            return "";
        }
        return obj.toString();
    }

    /**
     * @description: 字符串去左右空格
     * @param obj
     * @return java.lang.String
     * @author zmc
     * @date 2019-09-10 17:17
     */
    public static String trim(Object obj) {
        String str = changeNullToEmpty(obj);
        str = str.trim();
        return str;
    }

}
