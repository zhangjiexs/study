package com.uplooking.bigdata.analysis.domain.session;


import lombok.Data;

/**
 #top10_category表，存储按点击、下单和支付排序出来的top10品类数据
 CREATE TABLE `top10_category` (
 `task_id` int(11) NOT NULL,
 `category_id` int(11) DEFAULT NULL,
 `click_count` int(11) DEFAULT NULL,
 `order_count` int(11) DEFAULT NULL,
 `pay_count` int(11) DEFAULT NULL
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 */
@Data
public class Top10Category {
    private long task_id;
    private long category_id;
    private long click_count;
    private long order_count;
    private long pay_count;
}
