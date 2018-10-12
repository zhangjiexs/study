package com.uplooking.bigdata.core.d4

import java.sql.{DriverManager, PreparedStatement}

import com.uplooking.bigdata.util.{ConnectionPool, SparkUtil}
import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

/**
  * @ Title: 2018-01-16SparkStudy
  * @ Package:com.uplooking.bigdata.core.d4
  * @ description: 用ForeachPartitions方法通过MySQL保存数据
  * @ This program is protected by copyright laws
  * @ author zhangjie
  * @ date 2018/1/22 
  * @ version V1.0
  */
object ForeachPartitionsPractive {


  def main(args: Array[String]): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.apache.hadoop").setLevel(Level.WARN)
    Logger.getLogger("org.spark-project").setLevel(Level.WARN)
    val su = SparkUtil.getSparkContext(s"${ForeachPartitionsPractive.getClass.getSimpleName}", "local[2]")
    val lineRdd = su.textFile("D:/work/test.txt");
    //加载数据
    val dataRdd = lineRdd.flatMap(line => line.split(" "))
    val rbkRdd: RDD[(String, Int)] = dataRdd.map((_, 1)).reduceByKey(_ + _)


    foreach01(su,rbkRdd)
    //foreachPartition02(su,rbkRdd)
    //foreachPartition03(su,rbkRdd)
    //foreachPartition04(su)
      //foreachPartition05(su)
   su.stop()


  }

  /**
    * ①将统计的结果保存到数据库
    *
    * @param su
    * @param rbkRdd
    */
  def foreach01(su: SparkContext, rbkRdd: RDD[(String, Int)]) = {
    rbkRdd.foreach { case (data, number) => {

      //1、注册驱动
      classOf[com.mysql.jdbc.Driver]
      //2、建立连接
      val url = "jdbc:mysql://localhost:3306/student"
      val user = "root"
      val password = "258025"
      val connection = DriverManager.getConnection(url, user, password)
      //3、构建执行器
      val sql = "INSERT INTO test (data, number) VALUES(?, ?)"
      val prst: PreparedStatement = connection.prepareStatement(sql)
      prst.setString(1, data)
      prst.setInt(2, number)
      //4、执行SQL
      val et = prst.execute()
      //5、(如果有结果集)处理结果集
      if (et) {
        println("执行失败")
      } else {
        println("执行成功")
      }
      //6、释放资源
      prst.close()
      connection.close()

    }
    }
  }

  /**
    * ②将每个分区中的数据保存到数据库
    *
    * @param su
    * @param rbkRdd
    */
  def foreachPartition02(su: SparkContext, rbkRdd: RDD[(String, Int)]) = {
    rbkRdd.foreachPartition { case partition: Iterator[(String, Int)] => {
      //1、注册驱动
      classOf[com.mysql.jdbc.Driver]
      //2、建立连接
      val url = "jdbc:mysql://localhost:3306/student"
      val user = "root"
      val password = "258025"
      val connection = DriverManager.getConnection(url, user, password)
      //3、构建执行器
      val sql = "INSERT INTO test (data, number) VALUES(?, ?)"
      val prst: PreparedStatement = connection.prepareStatement(sql)
      partition.foreach { case (data, number) => {
        prst.setString(1, data)
        prst.setInt(2, number)
        prst.addBatch() //批量添加数据
      }
      }
      prst.executeBatch() //执行批处理
      prst.close()
      connection.close()
    }
    }
  }

  /**
    * ③拼接
    *
    * @param su
    * @param rbkRdd
    */
  def foreachPartition03(su: SparkContext, rbkRdd: RDD[(String, Int)]) = {
    rbkRdd.foreachPartition { case partition: Iterator[(String, Int)] => {
      //1、注册驱动
      classOf[com.mysql.jdbc.Driver]
      //2、建立连接
      val url = "jdbc:mysql://localhost:3306/student"
      val user = "root"
      val password = "258025"
      val connection = DriverManager.getConnection(url, user, password)
      val sb = new StringBuffer("INSERT INTO test (data, number) VALUES(")

      if (!partition.isEmpty) {
        partition.foreach { case (data, number) => {
          sb.append("'" + data + "'")
            .append(", ")
            .append(number)
            .append("), (")
        }
        }
        val sql = sb.substring(0, sb.lastIndexOf(","))
        val prst: PreparedStatement = connection.prepareStatement(sql)
        prst.execute()
        prst.close()
      }
      connection.close()
    }
    }
  }

  /**
    * ④分批次批量提交
    *
    * @param su
    */
  def foreachPartition04(su: SparkContext) = {
    //数据集合
    val list = List("zhang jie",
      "how are you", "I am fine",
      "thanks you and you",
      "I am fine too")
    val listRDD = su.parallelize(list)
    val retRDD: RDD[(String, Int)] = listRDD.flatMap(line => line.split(" ")).map((_, 1)).reduceByKey(_ + _, 1)
    retRDD.foreach(println)
    //每两个数据提交一次
    retRDD.foreachPartition { case (partition: Iterator[(String, Int)]) => {
      //1、注册驱动
      classOf[com.mysql.jdbc.Driver]
      //2、建立连接
      val url = "jdbc:mysql://localhost:3306/student"
      val user = "root"
      val password = "258025"
      val connection = DriverManager.getConnection(url, user, password)
      //3、构建执行器
      val sql = "INSERT INTO test (data, number) VALUES(?, ?)"
      val prst: PreparedStatement = connection.prepareStatement(sql)
      var count = 0
      partition.foreach { case (data, number) => {
        prst.setString(1, data)
        prst.setInt(2, number)
        prst.addBatch()

        count + 1
        if (count % 2 == 0) {
          prst.executeBatch()
          count = 0
        }
      }
      }
      if (count % 2 != 0) {
        prst.executeBatch()
      }
      prst.close()
      connection.close()
    }
    }}

  /**
    * ⑤分批次批量提交,使用连接池
    * @param su
    */
  def foreachPartition05(su: SparkContext) = {
    //数据集合
    val list = List("zhang jie",
      "how are you", "I am fine",
      "thanks you and you",
      "I am fine too")
    val listRDD = su.parallelize(list)
    val retRDD: RDD[(String, Int)] = listRDD.flatMap(line => line.split(" ")).map((_, 1)).reduceByKey(_ + _, 1)
    retRDD.foreach(println)
    // //每两个数据提交一次
    retRDD.foreachPartition{case (partition:Iterator[(String, Int)]) => {
      //创建连接池
      val connection = ConnectionPool.getConnection()
      //构建执行器
      val sql = "INSERT INTO test (data, number) VALUES(?, ?)"
      val prst:PreparedStatement = connection.prepareStatement(sql)
      var count = 0
      partition.foreach { case (data, number) => {
        prst.setString(1, data)
        prst.setInt(2, number)
        prst.addBatch()

        count + 1
        if (count % 2 == 0) {
          prst.executeBatch()
          count = 0
        }
      }
      }
      if (count % 2 != 0) {
        prst.executeBatch()
      }
      prst.close()
      ConnectionPool.returnConnection(connection)
    }}
  }

}