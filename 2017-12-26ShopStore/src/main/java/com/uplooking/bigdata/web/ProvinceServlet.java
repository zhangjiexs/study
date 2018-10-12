package com.uplooking.bigdata.web;

import com.alibaba.fastjson.JSON;
import com.uplooking.bigdata.biz.ProvinceBiz;
import com.uplooking.bigdata.vo.Province;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ Title: 2017-12-26ShopStore
 * @ Package:com.uplooking.bigdata.web
 * @ description: (describe the file in one sentence)
 * @ This program is protected by copyright laws
 * @ author zhangjie
 * @ date 2017/12/27
 * @ version V1.0
 */
public class ProvinceServlet extends HttpServlet {
     private    ProvinceBiz provinceBiz=new ProvinceBiz();

    /**
     * 处理省份servlet
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf8");
        try {
            List <Province> provinceList = provinceBiz.getPrvoinceList();
            List <String> provinces=new ArrayList<String>();
        
            for (Province province: provinceList) {
                provinces.add(province.getName());
            }
            String jsonret = JSON.toJSONString(provinces, true);
            //System.out.println(jsonret);
            resp.getWriter().write(jsonret);


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
