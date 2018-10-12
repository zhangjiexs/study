package com.uplooking.bigdata;

import com.uplooking.bigdata.server.RpcServer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.Server;

import java.io.IOException;


public class ServerDriver {
    public static void main(String[] args) throws IOException {
        Configuration conf=new Configuration();
        Server server=new RPC.Builder(conf).setBindAddress("localhost").
                setPort(8888).setProtocol(IHelloService.class).
                setInstance(new RpcServer()).build();
        server.start();
    }
}
