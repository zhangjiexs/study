package com.uplooking.bigdata.dao;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.uplooking.bigdata.until.DataSourceUtil;
import com.uplooking.bigdata.vo.Province;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.sql.DataSource;
import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

/**
 * @ Title: 2017-12-26ShopStore
 * @ Package:com.uplooking.bigdata.dao
 * @ description: (describe the file in one sentence)
 * @ This program is protected by copyright laws
 * @ author zhangjie
 * @ date 2017/12/27
 * @ version V1.0
 */
public class ProvinceDao {
     private    ComboPooledDataSource dt=DataSourceUtil.getDataSource();
      private   QueryRunner queryRunner = new QueryRunner(dt);
    /**
     * 查找所有省份
     * @return
     * @throws SQLException
     */
    public List<Province> findAllProvince() throws SQLException {
        String sql="select * from province";
        List<Province> provinceList=queryRunner.query(sql,new BeanListHandler<Province>(Province.class));
        //System.out.println(provinceList);
        return provinceList;
    }

    /**d
     * 根据身份查Id
     * @param pname
     * @return
     * @throws SQLException
     */

    public int findProviceID(String pname) throws SQLException {
        String sql="select * from province where name=?";
        Integer id = queryRunner.query(sql, new ScalarHandler<Integer>("Id"), pname);
        return id;
}
}
