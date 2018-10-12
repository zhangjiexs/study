package com.uplooking.bigdata.web;

import com.alibaba.fastjson.JSON;
import com.uplooking.bigdata.until.JedisUtil;
import com.uplooking.bigdata.vo.EcharsResult;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

/**
 * @ Title: 2017-12-26ShopStore
 * @ Package:com.uplooking.bigdata.web
 * @ description: (describe the file in one sentence)
 * @ This program is protected by copyright laws
 * @ author zhangjie
 * @ date 2018/1/2
 * @ version V1.0
 */
public class ShopCountServlet extends HttpServlet{
    private Jedis jedis=null;
    private ArrayList<Integer> valueList=null;
    private ArrayList<String>  keyList=null;

    @Override
    public void init() throws ServletException {
        jedis= JedisUtil.getJedis();
        valueList = new ArrayList<Integer>();
        keyList =new ArrayList<String>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        valueList.clear();
        keyList.clear();
        resp.setContentType("text/html;charset=utf-8");

        Set <String> keys=jedis.keys("*");
        for (String key:keys) {
            keyList.add(key);
            valueList.add(Integer.valueOf(jedis.get(key)));
        }
            EcharsResult echarsResult=new EcharsResult();
            echarsResult.setProvinceNames(keyList);
            echarsResult.setOrderCounts(valueList);
            String json = JSON.toJSONString(echarsResult, false);
            resp.getWriter().write(json);
    }
}
