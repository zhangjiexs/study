package com.uplooking.bigdata.analysis.util;

import com.uplooking.bigdata.analysis.conf.ConfigurationManager;
import com.uplooking.bigdata.analysis.constants.RunMode;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.commons.dbcp.managed.BasicManagedDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * DBCP连接池的工具类
 * 主要为外界提供一个DataSource或者Connection
 * 单例
 */
public class DBCPUtil {
    private DBCPUtil(){}

    private static DataSource ds ;
    static {
        InputStream in = null;
        Properties properties;
        if(ConfigurationManager.runMode.equals(RunMode.LOCAL)) {//加载本地
            in = DBCPUtil.class.getClassLoader().getResourceAsStream("local/dbcp-config.properties");
        } else if(ConfigurationManager.runMode.equals(RunMode.TEST)) {//加载测试
            in = DBCPUtil.class.getClassLoader().getResourceAsStream("test/dbcp-config.properties");
        } else if(ConfigurationManager.runMode.equals(RunMode.PRODUCTION)) {//加载生成
            in = DBCPUtil.class.getClassLoader().getResourceAsStream("production/dbcp-config.properties");
        }else {
            throw new ExceptionInInitializerError();
        }
        properties = new Properties();
        try {
            properties.load(in);
            ds = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static DataSource getDataSource() {
        return ds;
    }

    public static Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
