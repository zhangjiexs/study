package com.uplooking.bigdata.analysis.domain.session;


import lombok.Data;

/**
 #session_detail，用来存储随机抽取出来的session的明细数据、top10品类的session的明细数据
 CREATE TABLE `session_detail` (
 `task_id` int(11) NOT NULL,
 `user_id` int(11) DEFAULT NULL,
 `session_id` varchar(255) DEFAULT NULL,
 `page_id` int(11) DEFAULT NULL,
 `action_time` varchar(255) DEFAULT NULL,
 `search_keyword` varchar(255) DEFAULT NULL,
 `click_category_id` int(11) DEFAULT NULL,
 `click_product_id` int(11) DEFAULT NULL,
 `order_category_ids` varchar(255) DEFAULT NULL,
 `order_product_ids` varchar(255) DEFAULT NULL,
 `pay_category_ids` varchar(255) DEFAULT NULL,
 `pay_product_ids` varchar(255) DEFAULT NULL
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 */
@Data
public class SessionDetail {
    private long task_id;
    private long user_id;
    private String session_id;
    private long page_id;
    private String action_time;
    private String search_keyword;
    private long click_category_id;
    private long click_product_id;
    private String order_category_ids;
    private String order_product_ids;
    private String pay_category_ids;
    private String pay_product_ids;
}
