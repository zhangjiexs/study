package com.uplooking.bigdata;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class RedistTest {
private Jedis jedis;
    @Before
    public void init(){
        /*
        * 创建Redis的实例
        */
            String host="uplooking01";
            int port=6379;
            jedis= new Jedis(host,port);
    }
    @Test
    /*
    * 操作string
    * */
    public void TestString(){
        jedis.mset("k1","v1","k2","v2"); //批量增加数据

        jedis.set("k3", "v3");//增加一个数据
        String k3 = jedis.get("k3");//获取数据
        System.out.println(k3);//
        jedis.del("k1");//删除数据
        System.out.println("k1==>" + jedis.get("k1"));

    }
    /*
    * 操作hash
    * */
    @Test
    public void TestHash(){
        HashMap<String, String> score = new HashMap<>();
        //添加成绩
        score.put("math","97");
        score.put("english", "26");
        jedis.hmset("score", score);
        System.out.println("english===>" + jedis.hmget("score", "english"));
        //修改成绩
        score.put("english", "99");
        jedis.hmset("score", score);
        System.out.println("english===>" + jedis.hmget("score", "english"));
        //查询成绩
        jedis.hdel("score", "english");
        System.out.println("english===>" + jedis.hmget("score", "english"));
    }
    @Test
    /*
    * 操作list
    * */
    public void TestList(){
        //添加数据
      //jedis.lpush("loves", "java", "c++", "hadoop");
        //删除数据
       //jedis.rpop("loves");
        //修改数据
        //jedis.lset("loves", 1, "c#");
        //查询数据
        List<String> stringList = jedis.lrange("loves", 0, jedis.llen("loves"));
        System.out.println(stringList);

    }
    /*
    * 操作set
    * */
    @Test
    public void TestSet(){
        jedis.sadd("love","math","english");
        Set<String> love = jedis.smembers("love");
        System.out.println(love);

    }
    @Test
    /*
    * 操作zet
    * */
    public void TestZet(){
        jedis.zadd("name",56,"admin");
        jedis.zadd("name",158,"xiaohua");
        jedis.zadd("name",168,"xiaohonghong");
        Set<String> name = jedis.zrangeByScore("name", 100, 190);
        System.out.println(name);

    }
    /**
     * 删除当前所在的数据库中的数据
     */
    @Test
    public void testflushDBOps() {
        jedis.flushDB();
        System.out.println("当前数据库已经清空....");
    }


    /**
     * 删除所有数据库中的数据
     */
    @Test
    public void testflushAllOps() {
        jedis.flushAll();
        System.out.println("redis所有数据库都已经清空");
    }
}
