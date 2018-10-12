package com.uplooking.bigdata.analysis.domain.session;


import lombok.Data;

/**
 #task表，用来存储J2EE平台插入其中的任务的信息
 CREATE TABLE `task` (
 `task_id` int(11) NOT NULL AUTO_INCREMENT,
 `task_name` varchar(255) DEFAULT NULL,
 `create_time` varchar(255) DEFAULT NULL,
 `start_time` varchar(255) DEFAULT NULL,
 `finish_time` varchar(255) DEFAULT NULL,
 `task_type` varchar(255) DEFAULT NULL,
 `task_status` varchar(255) DEFAULT NULL,
 `task_param` text,
 PRIMARY KEY (`task_id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;
 */
@Data
public class Task {
    private long task_id;
    private String task_name;
    private String create_time;
    private String start_time;
    private String finish_time;
    private String task_type;
    private String task_status;
    private String task_param;
}
