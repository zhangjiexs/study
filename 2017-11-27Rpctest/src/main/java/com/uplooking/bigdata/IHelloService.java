package com.uplooking.bigdata;

import org.apache.hadoop.ipc.VersionedProtocol;

   /*
   * 自定义协议接口
   */
public interface IHelloService extends VersionedProtocol {

     //定义一个ID
      public long versionID = 123456798L;
      //定义一个方法
      public void sayHello();

}
