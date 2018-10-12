package com.uplooking.bigdata.vo.jsonbean;

import com.uplooking.bigdata.vo.jsonbean.Legend;
import com.uplooking.bigdata.vo.jsonbean.Serie;
import com.uplooking.bigdata.vo.jsonbean.Title;
import com.uplooking.bigdata.vo.jsonbean.XAxis;

import java.util.Arrays;
import java.util.List;
public class ResultBean {
    public Legend legend = new Legend();
    public List series = Arrays.asList(new Serie());
    public Title title= new Title();
    public Object tooltip  =new Object();
    public XAxis xAxis =  new XAxis();
    public Object yAxis = new Object();
}
