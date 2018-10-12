package com.uplooking.bigdata.analysis.dao.impl.session;

import com.uplooking.bigdata.analysis.dao.session.ITop10CategorySessionDao;
import com.uplooking.bigdata.analysis.domain.session.Top10CategorySession;
import com.uplooking.bigdata.analysis.util.DBCPUtil;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;
import java.util.List;

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
public class Top10CategorySessionDaoImpl implements ITop10CategorySessionDao {
    private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
    private String sql = "INSERT INTO top10_category_session VALUES(?, ?, ?, ?)";

    @Override
    public void insert(Top10CategorySession entry) {
        try {
            qr.update(sql, entry.getTask_id(), entry.getCategory_id(),
                    entry.getSession_id(), entry.getClick_count());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertBatch(List<Top10CategorySession> entries) {
        Object[][] params = new Object[entries.size()][];
        for (int i = 0; i < entries.size(); i++) {
            Top10CategorySession entry = entries.get(i);
            Object[] obj = new Object[] {
                    entry.getTask_id(), entry.getCategory_id(),
                    entry.getSession_id(), entry.getClick_count()
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
