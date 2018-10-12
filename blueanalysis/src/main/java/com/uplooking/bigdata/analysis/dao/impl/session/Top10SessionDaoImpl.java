package com.uplooking.bigdata.analysis.dao.impl.session;

import com.uplooking.bigdata.analysis.dao.session.ITop10SessionDao;
import com.uplooking.bigdata.analysis.domain.session.Top10Session;
import com.uplooking.bigdata.analysis.util.DBCPUtil;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;
import java.util.List;

/**
 #top10_session表，用来存储J2EE平台拆入的top10 活跃session
 CREATE TABLE `top10_session` (
 `task_id` INT(11) NOT NULL,
 `category_id` INT(11) DEFAULT NULL,
 `session_id` VARCHAR(255) DEFAULT NULL,
 `click_count` INT(11) DEFAULT NULL
 ) ENGINE=INNODB DEFAULT CHARSET=utf8;
 */
public class Top10SessionDaoImpl implements ITop10SessionDao {
    private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
    private String sql = "INSERT INTO top10_session VALUES(?, ?, ?, ?)";


    @Override
    public void insert(Top10Session entry) {
        try {
            qr.update(sql, entry.getTask_id(), entry.getCategory_id(),
                    entry.getSession_id(), entry.getClick_count());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertBatch(List<Top10Session> entries) {
        Object[][] params = new Object[entries.size()][];
        for (int i = 0; i < entries.size(); i++) {
            Top10Session entry = entries.get(i);
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
