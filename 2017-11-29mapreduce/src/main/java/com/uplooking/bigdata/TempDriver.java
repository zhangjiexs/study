package com.uplooking.bigdata;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;

public class TempDriver {
    public static void main(String[] args) {
        Configuration conf=new Configuration();

        Job job=Job.getInstance(conf,jobName);
    }
}
