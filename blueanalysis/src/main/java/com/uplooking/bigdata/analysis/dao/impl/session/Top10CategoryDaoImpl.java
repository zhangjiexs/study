package com.uplooking.bigdata.analysis.dao.impl.session;

import com.uplooking.bigdata.analysis.dao.session.ITop10CategoryDao;
import com.uplooking.bigdata.analysis.domain.session.Top10Category;
import com.uplooking.bigdata.analysis.util.DBCPUtil;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;
import java.util.List;

/**
 #top10_category表，存储按点击、下单和支付排序出来的top10品类数据
 CREATE TABLE `top10_category` (
 `task_id` int(11) NOT NULL,
 `category_id` int(11) DEFAULT NULL,
 `click_count` int(11) DEFAULT NULL,
 `order_count` int(11) DEFAULT NULL,
 `pay_count` int(11) DEFAULT NULL
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 */
public class Top10CategoryDaoImpl implements ITop10CategoryDao {
    private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
    private String sql = "INSERT INTO top10_category VALUES(?, ?, ?, ?, ?)";
    @Override
    public void insert(Top10Category entry) {
        try {
            qr.update(sql, entry.getTask_id(), entry.getCategory_id(), entry.getClick_count(),
                    entry.getOrder_count(), entry.getPay_count());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertBatch(List<Top10Category> entries) {
        Object[][] params = new Object[entries.size()][];
        for (int i = 0; i < entries.size(); i++) {
            Top10Category entry = entries.get(i);
            Object[] obj = new Object[] {
                    entry.getTask_id(), entry.getCategory_id(),
                    entry.getClick_count(),
                    entry.getOrder_count(), entry.getPay_count()
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
