package com.uplooking.bigdata;

import com.alibaba.fastjson.JSON;
import com.uplooking.bigdata.dao.ShopTypeDao;
import com.uplooking.bigdata.vo.Shop;
import com.uplooking.bigdata.vo.jsonbean.ResultBean;
import org.junit.Test;
import java.sql.SQLException;
import java.util.List;

public class ShopShowTest {
    @Test
    public void testFindAllShopCount() throws SQLException {
        ShopTypeDao shopTypeDao = new ShopTypeDao();
        List<Shop> shops = shopTypeDao.findAllShopCount();
        System.out.println(shops);
    }

    @Test
    public void testJson() {
        String jon = JSON.toJSONString(new ResultBean());
        System.out.println(jon);
    }
}
