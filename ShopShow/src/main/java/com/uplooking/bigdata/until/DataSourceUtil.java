package com.uplooking.bigdata.until;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceUtil {
    //创建c3p0的数据源
    private static ComboPooledDataSource dataSource ;
    public static ComboPooledDataSource getDataSource() {
        if (dataSource==null){
            dataSource= new ComboPooledDataSource();
        }
        return  dataSource;
    }
}
