package com.zhangjie.bigdata.util;

/**
 * @ Title: spilderL
 * @ Package:com.zhangjie.bigdata.util
 * @ description: c3p0连接池
 * @ This program is protected by copyright laws
 * @ author zhangjie
 * @ date 2018/3/25
 * @ version V1.0
 */

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 单例模式
 */
public class DataSourceUtil {
    //创建c3p0的数据源
    private static ComboPooledDataSource dataSource;

    public static ComboPooledDataSource getDataSource() {
        if (dataSource==null){
            dataSource=new ComboPooledDataSource();
        }
        return dataSource;
    }


}
