package com.uplooking.bigdata.sql.d2

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ Title: 2018-01-16SparkStudy
  * @ Package:com.uplooking.bigdata.sql.d2
  * @ description: (describe the file in one sentence)
  * @ This program is protected by copyright laws
  * @ author zhangjie
  * @ date 2018/1/31 
  * @ version V1.0
  */
object test{
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.apache.hadoop").setLevel(Level.WARN)
    Logger.getLogger("org.spark-project").setLevel(Level.WARN)
    val conf = new SparkConf().setAppName(s"${test.getClass.getSimpleName}").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val dataRDD= sc.textFile("D:/work/spark/a.txt")
    //统计每个单词出现的个数
    val tjRDD=dataRDD.flatMap(_.split(",")).map((_,1)).reduceByKey(_+_)
    tjRDD.foreach(println)

    sc.stop()
  }
}

