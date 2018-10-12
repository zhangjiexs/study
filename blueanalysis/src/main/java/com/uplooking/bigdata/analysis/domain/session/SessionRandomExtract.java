package com.uplooking.bigdata.analysis.domain.session;


import lombok.Data;

/**
 # session_random_extract表，存储我们的按时间比例随机抽取功能抽取出来的1000个session
 CREATE TABLE `session_random_extract` (
 `task_id` int(11) NOT NULL,
 `session_id` varchar(255) DEFAULT NULL,
 `start_time` varchar(50) DEFAULT NULL,
 `search_keywords` varchar(255) DEFAULT NULL,
 `click_category_ids` varchar(255) DEFAULT NULL
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 */
@Data
public class SessionRandomExtract {
    private long task_id;
    private String session_id;
    private String start_time;
    private String search_keywords;
    private String click_category_ids;

}
