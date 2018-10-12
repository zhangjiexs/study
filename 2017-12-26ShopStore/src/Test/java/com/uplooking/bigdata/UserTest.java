package com.uplooking.bigdata;

import com.uplooking.bigdata.dao.UserDao;
import com.uplooking.bigdata.vo.Province;
import org.junit.Test;

import java.sql.SQLException;

/**
 * @ Title: 2017-12-26ShopStore
 * @ Package:com.uplooking.bigdata
 * @ description: (describe the file in one sentence)
 * @ This program is protected by copyright laws
 * @ author zhangjie
 * @ date 2017/12/28
 * @ version V1.0
 */
public class UserTest {
    @Test
    public void test() throws SQLException {
        UserDao userDao=new UserDao();
        Province province = userDao.findByusername("root");
        System.out.println(province.getName());
    }
}
