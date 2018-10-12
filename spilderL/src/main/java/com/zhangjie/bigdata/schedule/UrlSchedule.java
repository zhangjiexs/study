package com.zhangjie.bigdata.schedule;

import com.zhangjie.bigdata.constant.PathConstant;
import com.zhangjie.bigdata.util.JedisUtil;
import com.zhangjie.bigdata.vo.Page;
import redis.clients.jedis.Jedis;

/**
 * @ Title: spilderL
 * @ Package:com.zhangjie.bigdata.schedule
 * @ description: (describe the file in one sentence)
 * @ This program is protected by copyright laws
 * @ author zhangjie
 * @ date 2018/3/25
 * @ version V1.0
 */

/**
 * url 调度器
 * 遵循队列的原则 先进先出
 *
 */
public class UrlSchedule {
    public static Jedis jedis= JedisUtil.getJedis();
    public static Page getListUrlFromRedis(Page page){
        jedis.select(1);
        String url= jedis.rpop("urllist");
        if (url != null){
            //在Redis 中还有列表页需要解析
            page.setUrl(PathConstant.BASE_PATH_BDTB+ url);
            return page;
        }
        return null;
    }
}
