package com.zhangjie.bigdata;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @ Title: FlowCount
 * @ Package:com.zhangjie.bigdata
 * @ description: 流量实体类(进行序列化)
 * @ This program is protected by copyright laws
 * @ author zhangjie
 * @ date 2018/3/26
 * @ version V1.0
 */
public class FlowBean implements Writable{
    /**
     * 完成上行流量和下行流量的封装
     */
    private long upFlow;//上行流量
    private long dfFlow;//下行流量
    private long FlowSum;//总流量

     //反序列化需要反射调用空参函数 显示构造一个
    public FlowBean(){}
    public FlowBean(long upFlow,long dfFlow){
        this.upFlow=upFlow;
        this.dfFlow=dfFlow;
        FlowSum=upFlow+dfFlow;

    }
    public long getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(long upFlow) {
        this.upFlow = upFlow;
    }

    public long getDfFlow() {
        return dfFlow;
    }

    public void setDfFlow(long dfFlow) {
        this.dfFlow = dfFlow;
    }

    public long getFlowSum() {
        return FlowSum;
    }

    public void setFlowSum(long flowSum) {
        FlowSum = flowSum;
    }

    /**
     * 序列化方法
     * 输出流
     * @param out
     * @throws IOException
     */
    public void write(DataOutput out) throws IOException {
        out.writeLong(upFlow);
        out.writeLong(dfFlow);
        out.writeLong(FlowSum);

    }

    /**
     * 反序列化方法
     * 输入流
     * @param in
     * @throws IOException
     */
    public void readFields(DataInput in) throws IOException {
        upFlow=in.readLong();
        dfFlow=in.readLong();
        FlowSum=in.readLong();

    }
    public String toString(){
        return upFlow + "\t" + dfFlow + "\t" + FlowSum;
    }
}
