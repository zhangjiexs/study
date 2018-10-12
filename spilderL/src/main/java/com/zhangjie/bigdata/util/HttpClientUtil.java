package com.zhangjie.bigdata.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @ Title: spilderL
 * @ Package:com.zhangjie.bigdata.util
 * @ description: 连接网页工具类
 * @ This program is protected by copyright laws
 * @ author zhangjie
 * @ date 2018/3/25
 * @ version V1.0
 */
public class HttpClientUtil {
    private  static HttpClient httpClien= HttpClientBuilder.create().build();
    private static String[]userAgent={
            "Mozilla/5.0 (Windows NT 6.1; rv:2.0.1) Gecko/20100101 Firefox/4.0.1",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; 360SE)"
    };

    /**
     * 使用传递的url下载数据  并且返回响应实体中的字符串
     * @param url 地址
     * @return 响应实体所代表的字符串
     */
    public static String getEntityStringByUrl(String url){
        try {
            HttpUriRequest httpUriRequest=new HttpGet(url);
            HttpResponse httpResponse=httpClien.execute(httpUriRequest);
            HttpEntity httpEntity=httpResponse.getEntity();
            String str= EntityUtils.toString(httpEntity);
            return str;

        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }
}
