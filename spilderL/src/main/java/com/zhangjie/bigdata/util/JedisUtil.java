package com.zhangjie.bigdata.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
public class JedisUtil {
    public static JedisPool pool = new JedisPool("127.0.0.1", 6379);
    public static Jedis getJedis() {
        return pool.getResource();

    }
}
