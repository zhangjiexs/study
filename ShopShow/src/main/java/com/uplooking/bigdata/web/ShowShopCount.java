package com.uplooking.bigdata.web;

import com.uplooking.bigdata.biz.ShopCountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ShowShopCount extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决乱码问题
        resp.setHeader("Content-Type","text/html;charset=utf-8");
        //创建Service对象,调用其方法获取json
        ShopCountService  shopCountService=new ShopCountService();
        String json=null;

        try {
            shopCountService.getShopTypeCount();
             resp.getWriter().write(json);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
