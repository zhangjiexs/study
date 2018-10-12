package com.uplooking.bigdata.until;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @ Title: 2017-12-29-ShopStoreStorm
 * @ Package:com.uplooking.bigdata
 * @ description: (describe the file in one sentence)
 * @ This program is protected by copyright laws
 * @ author zhangjie
 * @ date 2017/12/29
 * @ version V1.0
 */
public class JedisUtil {
    public static JedisPool pool = new JedisPool("uplooking01", 6379);
    public static Jedis getJedis() {
        return pool.getResource();
    }
}
