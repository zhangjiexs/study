package com.uplooking.bigdata;


import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class HdfsTest {
    //创建FileSystem对象
    private FileSystem fs;
    @Before
    public void init() throws Exception{
        Configuration conf=new Configuration();
        //指定文件系统
        conf.set("fs.defaultFS","hdfs://uplooking01:9000");
        //获取文件系统对象
        fs=FileSystem.get(conf);

    }
    @Test
    public void listDir() throws IOException {
          FileStatus[]  filseStatuses=fs.listStatus(new Path("/"));
          for (FileStatus fileStatus:filseStatuses){
              System.out.println(fileStatus.getLen());
              System.out.println(fileStatus.isFile());
          }
    }
    /*创建目录*/
    @Test
    public  void  testCreateDir() throws IOException {
        fs.mkdirs(new Path("/aa"));
    }
    /*创建文件*/
     @Test
     public void testCreatFile() throws IOException {
         fs.create(new Path("/a2.txt"));
     }
    /*读文件*/
    @Test
    public void testReadFileContent() throws Exception {
        FSDataInputStream fsDataInputStream=fs.open(new Path("/a1.txt"));
        IOUtils.copyBytes(fsDataInputStream,System.out,1024);
    }
   /* 删除文件*/
    @Test
    public void testDelete() throws IOException {
        fs.delete(new Path("/a2.txt"));
        fs.delete(new Path("/aa"));
    }

    @After
    public  void  destory() throws IOException {
          fs.close();
    }

}
