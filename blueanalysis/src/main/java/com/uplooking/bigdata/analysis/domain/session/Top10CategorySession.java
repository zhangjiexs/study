package com.uplooking.bigdata.analysis.domain.session;


import lombok.Data;

/**
 #top10_category_session表，存储top10每个品类的点击top10的session
 CREATE TABLE `top10_category_session` (
 `task_id` int(11) NOT NULL,
 `category_id` int(11) DEFAULT NULL,
 `session_id` varchar(255) DEFAULT NULL,
 `click_count` int(11) DEFAULT NULL,
 PRIMARY KEY (`task_id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 */
@Data
public class Top10CategorySession {
    private long task_id;
    private long category_id;
    private long click_count;
    private String session_id;
}
