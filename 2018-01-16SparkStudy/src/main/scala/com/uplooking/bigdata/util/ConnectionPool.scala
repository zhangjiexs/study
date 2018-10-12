package com.uplooking.bigdata.util
import java.util
import java.sql.{Connection, DriverManager, ResultSet}

/**
  * @ Title: 2018-01-16SparkStudy
  * @ Package:com.uplooking.bigdata.util
  * @ description: 连接池
  * @ This program is protected by copyright laws
  * @ author zhangjie
  * @ date 2018/1/22 
  * @ version V1.0
  */
object ConnectionPool {

  private val pool:util.LinkedList[Connection] = new util.LinkedList[Connection]()
  //java中的话 在构造代码块中完成如下初始化操作
  classOf[com.mysql.jdbc.Driver]
  for(i <- 1 to 10) {
    val url = "jdbc:mysql://localhost:3306/student"
    val user = "root"
    val password = "258025"
    pool.push(DriverManager.getConnection(url, user, password))
  }

  def getConnection():Connection = {
    while(pool.isEmpty) {
      Thread.sleep(1000)
    }
    //池子中有连接
    pool.poll()
  }

  def returnConnection(connection: Connection): Unit = {
    pool.push(connection)
  }

}
