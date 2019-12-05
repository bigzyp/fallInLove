package com.love.utils;

import com.love.enums.CodeMessageEnum;
import com.love.enums.LoveRuntimeException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 功能描述:
 *
 * @author: Mr.XIE
 * @date: 2018/9/4 18:11
 * @param:
 * @return:
 */
public class HttpUtil {


    /**
     * 封装HTTP POST方法
     *
     * @param
     * @param
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String post(String url, Map<String, Object> paramMap) {

        HttpResponse response = null;
        HttpPost httpPost = null;
        try {
            HttpClient httpClient = HttpClients.createDefault();
            httpPost = new HttpPost(url);
            List<NameValuePair> formparams = setHttpParams(paramMap);
            UrlEncodedFormEntity param = new UrlEncodedFormEntity(formparams, "UTF-8");
            httpPost.setEntity(param);
            response = httpClient.execute(httpPost);
        } catch (IOException e) {
            LogUtil.error(e,"HttpUtil.post  paramMap error!");
        }
        String httpEntityContent = getHttpEntityContent(response);
        httpPost.abort();
        return httpEntityContent;
    }

    /**
     * 封装HTTP POST方法
     *
     * @param
     * @param （如JSON串）
     * @return
     */
    public static String post(String url, String data) {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "text/json; charset=utf-8");

        HttpResponse response = null;
        try {
            httpPost.setEntity(new StringEntity(URLEncoder.encode(data, "UTF-8")));
            response = httpClient.execute(httpPost);
        } catch (IOException e) {
            LogUtil.error(e,"HttpUtil.post data error!");
        }
        String httpEntityContent = getHttpEntityContent(response);
        httpPost.abort();
        return httpEntityContent;
    }

    public static String postJson(String url, String data, Map<String, Object> headMap){
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json; charset=utf-8");
        if (!ObjectUtils.isEmpty(headMap)){
            for (Map.Entry<String, Object> entry : headMap.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue().toString());
            }
        }
        HttpResponse response = null;
        try {
            httpPost.setEntity(new StringEntity(data, "UTF-8"));
            response = httpClient.execute(httpPost);
            String httpEntityContent = EntityUtils.toString(response.getEntity());
            httpPost.abort();
            return httpEntityContent;
        } catch (Exception e){
            LogUtil.error(e, "请求失败 url:" + url + ", data:" + data);
            throw new LoveRuntimeException(CodeMessageEnum.SERVICE_ERROR.getCode(), "请求接口失败");
        }
    }

    /**
     * 封装HTTP GET方法
     *
     * @param
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String get(String url) {
        return get(url, false);
    }
    public static String get(String url, boolean contentType) {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet();
        if (contentType){
            httpGet.setHeader("Content-Type", "application/json; charset=utf-8");
        }
        httpGet.setURI(URI.create(url));
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            LogUtil.error(e,"HttpUtil.get error!");
        }
        String httpEntityContent = getHttpEntityContent(response);
        httpGet.abort();
        return httpEntityContent;
    }

    /**
     * 封装HTTP GET方法
     *
     * @param
     * @param
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String get(String url, Map<String, Object> paramMap) {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet();
        List<NameValuePair> formparams = setHttpParams(paramMap);
        String param = URLEncodedUtils.format(formparams, "UTF-8");
        httpGet.setURI(URI.create(url + "?" + param));
        httpGet.setHeader("Content-Type", "application/json; charset=utf-8");
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            LogUtil.error(e,"HttpUtil.get paramMap error!");
        }
        String httpEntityContent = getHttpEntityContent(response);
        httpGet.abort();
        return httpEntityContent;
    }

    /**
     * 封装HTTP PUT方法
     *
     * @param
     * @param
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String put(String url, Map<String, Object> paramMap) {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(url);
        List<NameValuePair> formparams = setHttpParams(paramMap);
        UrlEncodedFormEntity param = null;
        HttpResponse response = null;
        try {
            param = new UrlEncodedFormEntity(formparams, "UTF-8");
            httpPut.setEntity(param);
            response = httpClient.execute(httpPut);
        } catch (IOException e) {
            LogUtil.error(e,"HttpUtil.put paramMap error!");
        }
        String httpEntityContent = getHttpEntityContent(response);
        httpPut.abort();
        return httpEntityContent;
    }

    /**
     * 封装HTTP DELETE方法
     *
     * @param
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String delete(String url) {
        HttpClient httpClient = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete();
        httpDelete.setURI(URI.create(url));
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpDelete);
        } catch (IOException e) {
            LogUtil.error(e,"HttpUtil.delete error!");
        }
        String httpEntityContent = getHttpEntityContent(response);
        httpDelete.abort();
        return httpEntityContent;
    }

    /**
     * 设置请求参数
     *
     * @param
     * @return
     */
    private static List<NameValuePair> setHttpParams(Map<String, Object> paramMap) {
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        Set<Map.Entry<String, Object>> set = paramMap.entrySet();
        for (Map.Entry<String, Object> entry : set) {
            formparams.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
        }
        return formparams;
    }

    /**
     * 获得响应HTTP实体内容
     *
     * @param response
     * @return
     * @throws IOException
     * @throws UnsupportedEncodingException
     */
    private static String getHttpEntityContent(HttpResponse response) {
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            try {
                InputStream is = entity.getContent();
                BufferedReader br = null;
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                String line = br.readLine();
                StringBuilder sb = new StringBuilder();
                while (line != null) {
                    sb.append(line + "\n");
                    line = br.readLine();
                }
                return sb.toString();
            } catch (UnsupportedEncodingException e) {
                LogUtil.error(e,"HttpUtil.getHttpEntityContent UnsupportedEncodingException error!");
            } catch (IOException e) {
                LogUtil.error(e,"HttpUtil.getHttpEntityContent IOException error!");
            }
        }
        return "";
    }

    public static HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }
}
