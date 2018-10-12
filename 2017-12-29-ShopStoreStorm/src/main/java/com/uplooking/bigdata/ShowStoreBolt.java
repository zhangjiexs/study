package com.uplooking.bigdata;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;
import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * @ Title: 2017-12-29-ShopStoreStorm
 * @ Package:com.uplooking.bigdata
 * @ description: (describe the file in one sentence)
 * @ This program is protected by copyright laws
 * @ author zhangjie
 * @ date 2017/12/29
 * @ version V1.0
 */
public class ShowStoreBolt extends BaseRichBolt {
    private OutputCollector collector;
    private Jedis jedis;

    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {

         jedis = JedisUtil.getJedis();
        //在原有数量上加1,防止重新载入
        this.collector=collector;
    }

    @Override
    public void execute(Tuple tuple) {
        byte[] binary = tuple.getBinary(0);
           String provinceName=new String(binary);
        System.out.println("--------storm收到数据::" + provinceName + "------------------------");
        //存储到mysql中是不合理的   因为实时数据量非常大 而且要非常频繁的访问
        //解决方案   存储到redis
        if (jedis.exists(provinceName)){
            int count = Integer.valueOf(jedis.get(provinceName));
            jedis.set(provinceName,String.valueOf((count+1)));
        }else {
            jedis.set(provinceName,String.valueOf(1));
        }
        //确认消息   防止消息重复的发送
            collector.ack(tuple);
    }
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }
}
