package com.uplooking.bigdata;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class ShopMapper extends Mapper<LongWritable,Text,Text,IntWritable> {
    @Override
    protected void map(LongWritable index, Text line, Context context) throws IOException, InterruptedException {
     //读取数据,封装成数组
       String[] splits= line.toString().split(",");
       if (splits.length==0 || splits==null){
           return;
       }
        String shopType = splits[2];
        context.write(new Text(shopType),new IntWritable(1));
    }
}
