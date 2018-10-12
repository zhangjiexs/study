package com.uplooking.bigdata;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import java.io.IOException;

public class ShopDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf=new Configuration();
       //本地Hadoop模拟
        conf.set("fs.defaultFS","hdfs://uplooking01:9000");
        //写入数据库
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/uplooking?characterEncoding=utf8";
        DBConfiguration.configureDB(conf, driver, url, "root", "258025");
        String jobName = ShopDriver.class.getSimpleName();
        //创建job
        Job job=Job.getInstance(conf,jobName);
        //创建jar包
        job.setJarByClass(ShopDriver.class);
        //
        job.setNumReduceTasks(2);
        //设置调用所使用的Mapper类
        job.setMapperClass(ShopMapper.class);
        //设置调用所使用的Reducer类
        job.setReducerClass(ShopReducer.class);
        //设置输入处理器
        job.setInputFormatClass(TextInputFormat.class);
        //设置输入的路径
        TextInputFormat.setInputPaths(job,new Path("/shop/input"));
        //设置输出处理器
        job.setOutputFormatClass(DBOutputFormat.class);
        //设置输出内容
        DBOutputFormat.setOutput(job,"shop","type","count");
        //设置map输入类型和值
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //设置reduce输入类型和值
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //提交
        job.waitForCompletion(true);
    }
}
