package com.uplooking.bigdata.biz;

import com.uplooking.bigdata.dao.UserDao;
import com.uplooking.bigdata.vo.Province;
import com.uplooking.bigdata.vo.User;

import java.sql.SQLException;

/**
 * @ Title: 2017-12-26ShopStore
 * @ Package:com.uplooking.bigdata.biz
 * @ description: (describe the file in one sentence)
 * @ This program is protected by copyright laws
 * @ author zhangjie
 * @ date 2017/12/27
 * @ version V1.0
 */
public class UserBiz {
  private   UserDao userDao = new UserDao();
    /**
     * 用户注册
     */


    public boolean regist(User user) throws SQLException {
        int in = userDao.addUser(user);
        if (in > 0) {
            return true;
        }
        return false;
    }

    /**
     * 用户登录
     */
    public boolean login(User user) throws SQLException {

        User u = userDao.findByusernameuserpassword(user.getUsername(), user.getPassword());
        if (u != null) {
            return true;
        }
        return  false;
    }
    /**
     * 查找用户
     */
    public Province findProviceByusername(String usernme) throws SQLException {

       return userDao.findByusername(usernme);

    }
}
