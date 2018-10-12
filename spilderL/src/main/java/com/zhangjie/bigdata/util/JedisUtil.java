package com.zhangjie.bigdata.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
public class JedisUtil {
    public static JedisPool pool = new JedisPool("10.10.10.21", 6379);
    public static Jedis getJedis() {
        return pool.getResource();

    }
}
