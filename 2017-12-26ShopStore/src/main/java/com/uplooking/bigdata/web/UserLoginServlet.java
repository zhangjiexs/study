package com.uplooking.bigdata.web;

import com.uplooking.bigdata.biz.UserBiz;
import com.uplooking.bigdata.until.Md5Utils;
import com.uplooking.bigdata.vo.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @ Title: 2017-12-26ShopStore
 * @ Package:com.uplooking.bigdata.web
 * @ description: (describe the file in one sentence)
 * @ This program is protected by copyright laws
 * @ author zhangjie
 * @ date 2017/12/27
 * @ version V1.0
 */
public class UserLoginServlet extends HttpServlet{

     private    UserBiz userBiz=new UserBiz();

    /**
     * 用户登录servlet
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf8");
        String username=req.getParameter("username");
        String password= Md5Utils.md5( req.getParameter("password"));
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);

        try {
            boolean ir=userBiz.login(user);
            if (ir){
                req.getSession().setAttribute("username",user.getUsername());
                resp.sendRedirect("/showshop");
            }
            else {
                resp.getWriter().write("登录失败");
                resp.sendRedirect("/html/login.html");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
