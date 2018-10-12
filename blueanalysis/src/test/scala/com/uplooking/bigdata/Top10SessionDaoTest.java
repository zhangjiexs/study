package com.uplooking.bigdata;

import com.uplooking.bigdata.analysis.dao.impl.session.Top10SessionDaoImpl;
import com.uplooking.bigdata.analysis.dao.session.ITop10SessionDao;
import com.uplooking.bigdata.analysis.domain.session.Top10Session;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thinkpad on 2018/2/1.
 */
public class Top10SessionDaoTest {
    private ITop10SessionDao dao = new Top10SessionDaoImpl();
    @Test
    public void testInsert() {

        Top10Session entry = new Top10Session();
        entry.setTask_id(1);
        entry.setCategory_id(1);
        entry.setClick_count(1);
        entry.setSession_id("1");
        dao.insert(entry);
    }

    @Test
    public void testInsertBatch() {

        List<Top10Session> entries = new ArrayList<>();
        Top10Session entry1 = new Top10Session();
        entry1.setTask_id(3);
        entry1.setCategory_id(3);
        entry1.setClick_count(3);
        entry1.setSession_id("3");
        Top10Session entry2 = new Top10Session();
        entry2.setTask_id(2);
        entry2.setCategory_id(2);
        entry2.setClick_count(2);
        entry2.setSession_id("2");
        entries.add(entry1);
        entries.add(entry2);
        dao.insertBatch(entries);
    }
}
