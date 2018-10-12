CREATE DATABASE IF NOT EXISTS log_statics;

USE log_statics;
# session_aggr_stat表，存储第一个功能，session聚合统计的结果
CREATE TABLE IF NOT EXISTS  `session_aggr_stat` (
  `task_id` int(11) NOT NULL,
  `session_count` int(11) DEFAULT NULL,
  `visit_length_1s_3s_ratio` double DEFAULT NULL,
  `visit_length_4s_6s_ratio` double DEFAULT NULL,
  `visit_length_7s_9s_ratio` double DEFAULT NULL,
  `visit_length_10s_30s_ratio` double DEFAULT NULL,
  `visit_length_30s_60s_ratio` double DEFAULT NULL,
  `visit_length_1m_3m_ratio` double DEFAULT NULL,
  `visit_length_3m_10m_ratio` double DEFAULT NULL,
  `visit_length_10m_30m_ratio` double DEFAULT NULL,
  `visit_length_30m_ratio` double DEFAULT NULL,
  `step_length_1_3_ratio` double DEFAULT NULL,
  `step_length_4_6_ratio` double DEFAULT NULL,
  `step_length_7_9_ratio` double DEFAULT NULL,
  `step_length_10_30_ratio` double DEFAULT NULL,
  `step_length_30_60_ratio` double DEFAULT NULL,
  `step_length_60_ratio` double DEFAULT NULL,
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# session_random_extract表，存储我们的按时间比例随机抽取功能抽取出来的1000个session
CREATE TABLE `session_random_extract` (
  `task_id` int(11) NOT NULL,
  `session_id` varchar(255) DEFAULT NULL,
  `start_time` varchar(50) DEFAULT NULL,
  `search_keywords` varchar(255) DEFAULT NULL,
  `click_category_ids` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#top10_category表，存储按点击、下单和支付排序出来的top10品类数据
CREATE TABLE `top10_category` (
  `task_id` int(11) NOT NULL,
  `category_id` int(11) DEFAULT NULL,
  `click_count` int(11) DEFAULT NULL,
  `order_count` int(11) DEFAULT NULL,
  `pay_count` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#top10_category_session表，存储top10每个品类的点击top10的session
CREATE TABLE `top10_category_session` (
  `task_id` int(11) NOT NULL,
  `category_id` int(11) DEFAULT NULL,
  `session_id` varchar(255) DEFAULT NULL,
  `click_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


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

#top10_session表，用来存储J2EE平台拆入的top10 活跃session
CREATE TABLE `top10_session` (
  `task_id` INT(11) NOT NULL,
  `category_id` INT(11) DEFAULT NULL,
  `session_id` VARCHAR(255) DEFAULT NULL,
  `click_count` INT(11) DEFAULT NULL
) ENGINE=INNODB DEFAULT CHARSET=utf8;

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