package com.uplooking.bigdata.analysis.dao.impl;

import com.uplooking.bigdata.analysis.dao.ITaskDao;
import com.uplooking.bigdata.analysis.domain.session.Task;
import com.uplooking.bigdata.analysis.util.DBCPUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * ITaskDao的默认实现类，主要完成根据taskId查询出对应的任务的操作
 */
public class TaskDaoImpl implements ITaskDao {
    private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
    @Override
    public Task queryById(long id) {
        String sql =  "SELECT * FROM task WHERE task_id = ?";
        try {
            return qr.query(sql, new BeanHandler<Task>(Task.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
