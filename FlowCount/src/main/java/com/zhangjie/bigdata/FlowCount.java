package com.zhangjie.bigdata;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;


/**
 * @ Title: FlowCount
 * @ Package:com.zhangjie.bigdata
 * @ description: (describe the file in one sentence)
 * @ This program is protected by copyright laws
 * @ author zhangjie
 * @ date 2018/3/26
 * @ version V1.0
 */
public class FlowCount {
    /**
     * MapReduce
     * map阶段
     */
    static class FlowCountMapper extends Mapper<LongWritable, Text, Text, FlowBean> {
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            //①将一行数据转化为字符串
            String line = value.toString();
            //②切分字段
            String[] fields = line.split("\t");
            //③取出手机号字段
            String phoneNb=fields[1];
            //④取出上下行流量
            long upFlow = Long.parseLong(fields[fields.length - 3]);
            long dfFlow = Long.parseLong(fields[fields.length - 2]);
            //⑤用上下文写入Redis端
            context.write(new Text(phoneNb),new FlowBean(upFlow,dfFlow));
        }
    }

    /**
     * MapReduce
     * reduce阶段
     */
    static class FlowCountReducer extends Reducer<Text,FlowBean,Text,FlowBean>{
        @Override
        protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {

            //①流量汇总
               long sum_upFlow=0;
               long sum_dfFlow=0;
               for (FlowBean bean: values){
                   sum_upFlow +=bean.getUpFlow();
                   sum_dfFlow += bean.getDfFlow();

                   //②将统计结果写出去
                   FlowBean resultBean = new FlowBean(sum_upFlow,sum_dfFlow);
                   context.write(key,resultBean);
               }
        }
    }


}
