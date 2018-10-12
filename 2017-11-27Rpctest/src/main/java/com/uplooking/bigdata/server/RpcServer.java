package com.uplooking.bigdata.server;

import com.uplooking.bigdata.IHelloService;
import org.apache.hadoop.ipc.ProtocolSignature;

import java.io.IOException;
/**
 * 服务器端实现协议
 *
*/

public class RpcServer implements IHelloService {

    public void sayHello(){
        System.out.println("我是服务器端sayHello方法");
    }

    public long getProtocolVersion(String s, long l) throws IOException {
        return versionID;
    }

    public ProtocolSignature getProtocolSignature(String s, long l, int i) throws IOException {
        return new ProtocolSignature();
    }
}
