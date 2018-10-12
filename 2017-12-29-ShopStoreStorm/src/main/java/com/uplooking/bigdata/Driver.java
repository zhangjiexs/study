package com.uplooking.bigdata;

import kafka.api.OffsetRequest;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.kafka.BrokerHosts;
import org.apache.storm.kafka.KafkaSpout;
import org.apache.storm.kafka.SpoutConfig;
import org.apache.storm.kafka.ZkHosts;
import org.apache.storm.topology.TopologyBuilder;

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
public class Driver {
    public static void main(String[] args) {
        String topologyName = Driver.class.getSimpleName();
        TopologyBuilder topologyBuilder=new TopologyBuilder();
        //创建storm的配置对象
        Map conf=new Config();
        String zkRoot="/brokers";
        BrokerHosts brokerHosts=new ZkHosts("uplooking01:2181,uplooking02:2181,uplooking03:2181");
        SpoutConfig spoutConfig=new SpoutConfig(brokerHosts, "spark", zkRoot, "myid");
        //-1  设置 从最新的消息开始消费,防止从头消费
        spoutConfig.startOffsetTime = OffsetRequest.LatestTime();
        topologyBuilder.setSpout("kafkaSpoutForShop", new KafkaSpout(spoutConfig));
        topologyBuilder.setBolt("shopCountBolt", new ShowStoreBolt()).shuffleGrouping("kafkaSpoutForShop");
        StormTopology topology=topologyBuilder.createTopology();
        //本地模式提交
        LocalCluster localCluster=new LocalCluster();
        localCluster.submitTopology(topologyName, conf, topology);
    }
}
