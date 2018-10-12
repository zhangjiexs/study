package com.uplooking.bigdata.analysis.dao;


import java.util.List;

/**
 * Created by thinkpad on 2018/2/1.
 */
public interface BaseDao<T> {
    /**
     * 插入一条记录
     * @param entry
     */
    public void insert(T entry);

    /**
     * 批量插入
     * @param entries
     */
    public void insertBatch(List<T> entries);
}
