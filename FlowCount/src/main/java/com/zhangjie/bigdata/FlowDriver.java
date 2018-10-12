package com.zhangjie.bigdata;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;


import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


/**
 * @ Title: FlowCount
 * @ Package:com.zhangjie.bigdata
 * @ description: 驱动类
 * @ This program is protected by copyright laws
 * @ author zhangjie
 * @ date 2018/3/26
 * @ version V1.0
 */
public class FlowDriver {
    public static void main(String[] args) throws Exception {
        //拿到配置
        Configuration  conf=new Configuration();
        //把任务提交到yarn集群中
        Job job = Job.getInstance();
        //指定本地程序jar包所在本地路径
        job.setJarByClass(FlowCount.class);
        //指定本业务使用的mapper和reducer类
        job.setMapperClass(FlowCount.FlowCountMapper.class);
        job.setReducerClass(FlowCount.FlowCountReducer.class);
        //mapper输出的kv类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);
        //指定最终输出KV类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);
        //指定job输入的原始文件
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        //指定输出结果所在的路径
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        //反馈集群信息
        boolean res = job.waitForCompletion(true);
        System.exit(res?0:1);

    }
}
