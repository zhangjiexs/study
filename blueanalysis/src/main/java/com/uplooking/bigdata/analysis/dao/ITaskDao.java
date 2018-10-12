package com.uplooking.bigdata.analysis.dao;

import com.uplooking.bigdata.analysis.domain.session.Task;

/**
 * task表对应的数据持久层接口
 */
public interface ITaskDao {

    Task queryById(long id);
}
