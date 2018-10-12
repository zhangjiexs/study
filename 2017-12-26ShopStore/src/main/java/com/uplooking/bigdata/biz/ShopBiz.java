package com.uplooking.bigdata.biz;

import com.uplooking.bigdata.dao.ShopDao;
import com.uplooking.bigdata.vo.Shop;

import java.sql.SQLException;
import java.util.List;

/**
 * @ Title: 2017-12-26ShopStore
 * @ Package:com.uplooking.bigdata.biz
 * @ description: (describe the file in one sentence)
 * @ This program is protected by copyright laws
 * @ author zhangjie
 * @ date 2017/12/28
 * @ version V1.0
 */
public class ShopBiz {
     private    ShopDao shopDao = new ShopDao();

    /**
     * 商品列表
     * @return
     * @throws SQLException
     */
    public List<Shop> findShopList() throws SQLException {
        List<Shop> shops = shopDao.findAllShopList();
        return shops;
    }
}
