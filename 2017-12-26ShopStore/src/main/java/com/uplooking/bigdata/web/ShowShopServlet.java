package com.uplooking.bigdata.web;

import com.alibaba.fastjson.JSON;
import com.uplooking.bigdata.biz.ShopBiz;
import com.uplooking.bigdata.vo.Shop;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @ Title: 2017-12-26ShopStore
 * @ Package:com.uplooking.bigdata.web
 * @ description: (describe the file in one sentence)
 * @ This program is protected by copyright laws
 * @ author zhangjie
 * @ date 2017/12/28
 * @ version V1.0
 */
public class ShowShopServlet extends HttpServlet {
     private    ShopBiz shopBiz=new ShopBiz();

    /**
     * 商品列表Servlet
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
         List<Shop> shops=shopBiz.findShopList();
           // System.out.println(JSON.toJSONString(shops));
            req.setAttribute("shops",shops);
            //转发给jsp处理
            req.getRequestDispatcher("/jsp/shop.jsp").forward(req,resp);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
