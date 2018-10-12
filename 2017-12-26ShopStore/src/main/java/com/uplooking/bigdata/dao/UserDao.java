package com.uplooking.bigdata.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.uplooking.bigdata.until.DataSourceUtil;
import com.uplooking.bigdata.vo.Province;
import com.uplooking.bigdata.vo.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;

/**
 * @ Title: 2017-12-26ShopStore
 * @ Package:com.uplooking.bigdata.dao
 * @ description: (describe the file in one sentence)
 * @ This program is protected by copyright laws
 * @ author zhangjie
 * @ date 2017/12/27
 * @ version V1.0
 */
public class UserDao {
     private    ComboPooledDataSource dt=DataSourceUtil.getDataSource();
     private    QueryRunner queryRunner=new QueryRunner(dt);
    /**
     * 用户注册
     * @param user
     * @return
     * @throws SQLException
     */
    public int addUser(User user) throws SQLException {
       String sql="insert into user(username,password,province_id) values(?,?,?)";
        int update = queryRunner.update(sql, user.getUsername(), user.getPassword(), user.getProvice_id());
        return  update;
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     * @throws SQLException
     */
    public User findByusernameuserpassword(String username, String password) throws SQLException {

        String sql="select * from user where username=? and password=?";
        User user=queryRunner.query(sql, new BeanHandler<User>(User.class),username,password);
        return user;
    }

    /**
     * 查用用户所在省份
     * @param username
     * @return
     * @throws SQLException
     */
    public Province findByusername(String username) throws SQLException {

        String sql="select p.name from province as  p ,user as u  where p.id=u.province_id  and u.username=?";
        Province provinces = queryRunner.query(sql, new BeanHandler<Province>(Province.class), username);
        return provinces;
    }


}
