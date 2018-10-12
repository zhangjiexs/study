package com.uplooking.bigdata.analysis.dao.impl.session;

import com.uplooking.bigdata.analysis.dao.session.ISessionAggrStatDao;
import com.uplooking.bigdata.analysis.domain.session.SessionAggrStat;
import com.uplooking.bigdata.analysis.util.DBCPUtil;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;
import java.util.List;

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
public class SessionAggrStatDaoImpl implements ISessionAggrStatDao {
    private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
    private String sql = "INSERT INTO session_aggr_stat VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    @Override
    public void insert(SessionAggrStat entry) {
        try {
            qr.update(sql, entry.getTask_id(),
                    entry.getSession_count(),
                    entry.getVisit_length_1s_3s_ratio(), entry.getVisit_length_4s_6s_ratio(),
                    entry.getVisit_length_7s_9s_ratio(), entry.getVisit_length_10s_30s_ratio(),
                    entry.getVisit_length_30s_60s_ratio(), entry.getVisit_length_1m_3m_ratio(),
                    entry.getVisit_length_3m_10m_ratio(), entry.getVisit_length_10m_30m_ratio(),
                    entry.getVisit_length_30m_ratio(), entry.getStep_length_1_3_ratio(),
                    entry.getStep_length_4_6_ratio(), entry.getStep_length_7_9_ratio(),
                    entry.getStep_length_10_30_ratio(), entry.getStep_length_30_60_ratio(),
                    entry.getStep_length_60_ratio());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertBatch(List<SessionAggrStat> entries) {
        Object[][] params = new Object[entries.size()][];

        for (int i = 0; i < entries.size(); i++) {
            SessionAggrStat entry = entries.get(i);
            Object[] obj = new Object[]{
                    entry.getTask_id(), entry.getSession_count(),
                    entry.getVisit_length_1s_3s_ratio(), entry.getVisit_length_4s_6s_ratio(),
                    entry.getVisit_length_7s_9s_ratio(), entry.getVisit_length_10s_30s_ratio(),
                    entry.getVisit_length_30s_60s_ratio(), entry.getVisit_length_1m_3m_ratio(),
                    entry.getVisit_length_3m_10m_ratio(), entry.getVisit_length_10m_30m_ratio(),
                    entry.getVisit_length_30m_ratio(), entry.getStep_length_1_3_ratio(),
                    entry.getStep_length_4_6_ratio(), entry.getStep_length_7_9_ratio(),
                    entry.getStep_length_10_30_ratio(), entry.getStep_length_30_60_ratio(),
                    entry.getStep_length_60_ratio()
            };
            params[i] = obj;
        }
        try {
            qr.batch(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
