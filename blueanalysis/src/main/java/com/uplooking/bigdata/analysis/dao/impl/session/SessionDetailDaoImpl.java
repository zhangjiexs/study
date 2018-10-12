package com.uplooking.bigdata.analysis.dao.impl.session;

import com.uplooking.bigdata.analysis.dao.session.ISessionDetailDao;
import com.uplooking.bigdata.analysis.domain.session.SessionDetail;
import com.uplooking.bigdata.analysis.util.DBCPUtil;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;
import java.util.List;

/**
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
 */
public class SessionDetailDaoImpl implements ISessionDetailDao {
    private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
    private String sql = "INSERT INTO session_detail VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    public void insert(SessionDetail entry) {
        try {
            qr.update(sql, entry.getTask_id(), entry.getUser_id(), entry.getSession_id(),
                    entry.getPage_id(), entry.getAction_time(), entry.getSearch_keyword(),
                    entry.getClick_category_id(), entry.getClick_product_id(), entry.getOrder_category_ids(),
                    entry.getOrder_product_ids(), entry.getPay_category_ids(), entry.getPay_product_ids());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertBatch(List<SessionDetail> entries) {
        Object[][] params = new Object[entries.size()][];
        for (int i = 0; i < entries.size(); i++) {
            SessionDetail entry = entries.get(i);
            Object[] obj = new Object[]{
                    entry.getTask_id(), entry.getUser_id(),
                    entry.getSession_id(), entry.getPage_id(),
                    entry.getAction_time(), entry.getSearch_keyword(),
                    entry.getClick_category_id(), entry.getClick_product_id(),
                    entry.getOrder_category_ids(), entry.getOrder_product_ids(),
                    entry.getPay_category_ids(), entry.getPay_product_ids()

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
