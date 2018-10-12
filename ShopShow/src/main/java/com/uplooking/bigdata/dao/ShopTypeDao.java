package com.uplooking.bigdata.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.uplooking.bigdata.until.DataSourceUtil;

import com.uplooking.bigdata.vo.Shop;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ShopTypeDao {
    public List<Shop> findAllShopCount() throws SQLException {
        //使用数据源(连接池)  dbcp  c3p0   作为connection的管理器
        //DBUtils访问数据
        ComboPooledDataSource dataSource = DataSourceUtil.getDataSource();
        //DBUtils的执行器
        QueryRunner runner=new QueryRunner(dataSource);
        List<Shop> shops=runner.query("select * from shop;", new BeanListHandler<Shop>(Shop.class));
        return shops;
    }
}
