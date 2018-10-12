package com.uplooking.bigdata;

import com.sun.org.apache.xpath.internal.SourceTree;
import com.uplooking.bigdata.dao.ProvinceDao;
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
public class ProviceTest {
    @Test
    public void test() throws SQLException {
        ProvinceDao provinceDao=new ProvinceDao();
       int id = provinceDao.findProviceID("山西");
        System.out.println(id);

    }
}
