package com.uplooking.bigdata.core.d3

import com.uplooking.bigdata.util.SparkUtil
import org.apache.spark.broadcast.Broadcast


/**
  * @ Title: 2018-01-16SparkStudy
  * @ Package:com.uplooking.bigdata.core.d3
  * @ description: 使用spark的共享变量进行数据分析
  * @ This program is protected by copyright laws
  * @ author zhangjie
  * @ date 2018/1/17 
  * @ version V1.0
  */
object SparkBroadcastOps {
  /**
    * 广播变量
    *
    */
  def main(args: Array[String]): Unit = {
    val su=SparkUtil.getSparkContext(s"${SparkBroadcastOps.getClass.getCanonicalName}", "local[2]")

    val user = List(
      "001,赵飞燕,1583-05-20,0,河北,华北",
      "002,爱新觉罗玄烨,1783-02-17,1,北京,华北",
      "003,妲己,2002-09-08,0,河南,华中"
    )

    val genderMap = Map[String, String]("0"->"女", "1"->"男")
    val genderBC:Broadcast[Map[String, String]] = su.broadcast(genderMap)

    val userRDD = su.parallelize(user)
    userRDD.map{case line => {
      val fields = line.split(",")
      val genderCode = fields(3).trim
      val gender = genderBC.value.getOrElse(genderCode, "男")
      fields(0) + "\t" + fields(1) + "\t" + fields(2) + "\t" + gender + "\t" +  fields(4) + "\t" + fields(5)
    }}.foreach(println)
    su.stop()



  }

}


