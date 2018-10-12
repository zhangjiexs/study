package com.uplooking.bigdata.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Title: 2017-12-26ShopStore
 * @ Package:com.uplooking.bigdata.vo
 * @ description: (describe the file in one sentence)
 * @ This program is protected by copyright laws
 * @ author zhangjie
 * @ date 2018/1/2
 * @ version V1.0
 */
public class EcharsResult {
    private List<String> provinceNames=new ArrayList<String>();//省份名称
    private List<Integer> orderCounts=new ArrayList<Integer>();//订单数

    public List<String> getProvinceNames() {
        return provinceNames;
    }

    public void setProvinceNames(List<String> provinceNames) {
        this.provinceNames = provinceNames;
    }

    public List<Integer> getOrderCounts() {
        return orderCounts;
    }

    public void setOrderCounts(List<Integer> orderCounts) {
        this.orderCounts = orderCounts;
    }
}
