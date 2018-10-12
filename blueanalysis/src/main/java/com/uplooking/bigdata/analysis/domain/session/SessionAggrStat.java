package com.uplooking.bigdata.analysis.domain.session;


import lombok.Data;

/**
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
 */
@Data
public class SessionAggrStat {
    private long task_id;
    private long session_count;
    private double visit_length_1s_3s_ratio;
    private double visit_length_4s_6s_ratio;
    private double visit_length_7s_9s_ratio;
    private double visit_length_10s_30s_ratio;
    private double visit_length_30s_60s_ratio;
    private double visit_length_1m_3m_ratio;
    private double visit_length_3m_10m_ratio;
    private double visit_length_10m_30m_ratio;
    private double visit_length_30m_ratio;
    private double step_length_1_3_ratio;
    private double step_length_4_6_ratio;
    private double step_length_7_9_ratio;
    private double step_length_10_30_ratio;
    private double step_length_30_60_ratio;
    private double step_length_60_ratio;


}
