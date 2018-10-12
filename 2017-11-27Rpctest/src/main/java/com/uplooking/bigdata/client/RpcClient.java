package com.uplooking.bigdata.client;

import com.uplooking.bigdata.IHelloService;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

public class RpcClient {
    public static void main(String[] args) throws IOException {
        Configuration conf=new Configuration();
        //远程调用
        InetSocketAddress inetSocketAddress=new InetSocketAddress("localhost",8888);
        //创建代理对象
        IHelloService proxy= RPC.getProxy(IHelloService.class, IHelloService.versionID, inetSocketAddress,
        conf);
        proxy.sayHello();
    }
}
