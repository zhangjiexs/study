package com.uplooking.bigdata.analysis.domain.session;


import lombok.Data;
/*
#top10_session表，用来存储J2EE平台拆入的top10 活跃session
CREATE TABLE `top10_session` (
  `task_id` INT(11) NOT NULL,
  `category_id` INT(11) DEFAULT NULL,
  `session_id` VARCHAR(255) DEFAULT NULL,
  `click_count` INT(11) DEFAULT NULL
) ENGINE=INNODB DEFAULT CHARSET=utf8;
 */
@Data
public class Top10Session {
    private long task_id;
    private long category_id;
    private long click_count;
    private String session_id;
}
