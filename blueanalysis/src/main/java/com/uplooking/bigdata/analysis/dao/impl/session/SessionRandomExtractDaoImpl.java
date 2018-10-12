package com.uplooking.bigdata.analysis.dao.impl.session;

import com.uplooking.bigdata.analysis.dao.session.ISessionRandomExtractDao;
import com.uplooking.bigdata.analysis.domain.session.SessionRandomExtract;
import com.uplooking.bigdata.analysis.util.DBCPUtil;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;
import java.util.List;

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
public class SessionRandomExtractDaoImpl implements ISessionRandomExtractDao {
    private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
    private String sql = "INSERT INTO session_random_extract VALUES(?, ?, ?, ?, ?)";

    @Override
    public void insert(SessionRandomExtract entry) {
        try {
            qr.update(sql, entry.getTask_id(), entry.getSession_id(), entry.getStart_time(),
                    entry.getSearch_keywords(), entry.getClick_category_ids());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertBatch(List<SessionRandomExtract> entries) {
        Object[][] params = new Object[entries.size()][];
        for (int i = 0; i < entries.size(); i++) {
            SessionRandomExtract entry = entries.get(i);
            Object[] obj = new Object[] {
                    entry.getTask_id(), entry.getSession_id(),
                    entry.getStart_time(), entry.getSearch_keywords(),
                    entry.getClick_category_ids()
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
