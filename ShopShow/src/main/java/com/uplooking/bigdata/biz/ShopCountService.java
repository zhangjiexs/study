package com.uplooking.bigdata.biz;

import com.alibaba.fastjson.JSON;
import com.uplooking.bigdata.dao.ShopTypeDao;
import com.uplooking.bigdata.vo.Shop;
import com.uplooking.bigdata.vo.jsonbean.ResultBean;
import com.uplooking.bigdata.vo.jsonbean.Serie;
import com.uplooking.bigdata.vo.jsonbean.XAxis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShopCountService {

    public String getShopTypeCount() throws SQLException {

        ShopTypeDao shopTypeDao=new ShopTypeDao();

        List<Shop> shops=shopTypeDao.findAllShopCount();

        List<String>   typeNames=new ArrayList<String>();
        List<Integer> typeCounts=new ArrayList<Integer>();
        for (Shop shop:shops){
            typeNames.add(shop.getType());
            typeCounts.add(shop.getCount());
        }

        Serie serie=new Serie();
        serie.data=typeCounts;

        XAxis xAxis=new XAxis();
        xAxis.data=typeNames;

        ResultBean rootBean=new ResultBean();
        rootBean.series=Arrays.asList(serie);
        rootBean.xAxis=xAxis;
        String json=JSON.toJSONString(rootBean);
        return json;
    }
}
