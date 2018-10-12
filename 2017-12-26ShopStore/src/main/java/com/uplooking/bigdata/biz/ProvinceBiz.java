package com.uplooking.bigdata.biz;

import com.uplooking.bigdata.dao.ProvinceDao;
import com.uplooking.bigdata.vo.Province;

import java.sql.SQLException;
import java.util.List;

/**
 * @ Title: 2017-12-26ShopStore
 * @ Package:com.uplooking.bigdata.biz
 * @ description: (describe the file in one sentence)
 * @ This program is protected by copyright laws
 * @ author zhangjie
 * @ date 2017/12/27
 * @ version V1.0
 */
public class ProvinceBiz {
    private     ProvinceDao provinceDao = new ProvinceDao();

    /**
     * 省份
     * @return
     * @throws SQLException
     */
    public List<Province> getPrvoinceList() throws SQLException {
        return provinceDao.findAllProvince();
    }

    public Integer getProvinceId(String pname) throws SQLException {

        int id= provinceDao.findProviceID(pname);
        return id;

    }
}
