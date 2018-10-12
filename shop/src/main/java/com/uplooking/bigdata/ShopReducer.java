package com.uplooking.bigdata;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class ShopReducer extends Reducer<Text,IntWritable,Shop,Shop> {
    @Override
    protected void reduce(Text type, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
       int sum=0;
        for (IntWritable value :values
             ) {
            sum+=value.get();
        }
       context.write(new Shop(type.toString(),sum),null);
    }
}
