package com.uplooking.bigdata.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.uplooking.bigdata.until.DataSourceUtil;
import com.uplooking.bigdata.vo.Shop;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @ Title: 2017-12-26ShopStore
 * @ Package:com.uplooking.bigdata.dao
 * @ description: (describe the file in one sentence)
 * @ This program is protected by copyright laws
 * @ author zhangjie
 * @ date 2017/12/28
 * @ version V1.0
 */
public class ShopDao {
     private    ComboPooledDataSource dt= DataSourceUtil.getDataSource();
      private   QueryRunner query=new QueryRunner(dt);
    /**
     * 查找出所有商品列表
     * @return
     * @throws SQLException
     */
    public List<Shop> findAllShopList() throws SQLException {
        String sql="select name from shop";
        List<Shop> shops=query.query(sql,new BeanListHandler<Shop>(Shop.class));
        return shops;
    }

}
