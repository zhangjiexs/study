package com.uplooking.bigdata.core.d2

import com.uplooking.bigdata.util.SparkUtil
import org.apache.hadoop.io.{IntWritable, Text}
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat
import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

/**
  * @ Title: 2018-01-16SparkStudy
  * @ Package:com.uplooking.bigdata.core.d2
  * @ description: 操作action算子
  * @ This program is protected by copyright laws
  * @ author zhangjie
  * @ date 2018/1/16 
  * @ version V1.0
  */
object ScalaSparkActionOps {
  def main(args: Array[String]): Unit = {

    //设置日志的级别
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.apache.hadoop").setLevel(Level.WARN)
    val su=SparkUtil.getSparkContext(s"${ScalaSparkActionOps.getClass.getCanonicalName}", "local[2]")
    val list = List(1, 2, 3, 4, 5, 6, 7)
    val  listRDD:RDD[Int]=su.parallelize(list)
    //val sum:Int=listRDD.reduce{case (v1, v2)=>v1+v2}
    //println("sum"+sum)//reduce均函数
    val ret:Array[Int]=listRDD.take(5)//take获取几条记录
    println(ret.mkString("[",",","]"))
    val  first=listRDD.first()//first获取第一条记录
    println("first :" +first)



       // actionOps1(su)
    //        actionOps2(su)
    //actionOps3(su)

        su.stop()
  }

  /**
    *  reduce均函数
    *  take获取几条记录
    *  first返回第一个元素
    * @param su
    */
  def actionOps1(su: SparkContext): Unit = {
    val list = List(1, 2, 3, 4, 5, 6, 7)

    val listRDD: RDD[Int] = su.parallelize(list)

    /* val sum:Int = listRDD.reduce{case (v1, v2) => v1 + v2}
    println("sum: " + sum)*/

    val ret: Array[Int] = listRDD.take(5)
    println(ret.mkString("[", ",", "]"))
    val first = listRDD.first()
    println("first: " + first)
  }

  /**
    *saveAsTextFile 读存操作
    * countByKey返回键值对
    * @param su
    */
  def actionOps2(su: SparkContext): Unit = {
    val list = List("guo zhao yang", "jie zhang", "de zhao chun yu")
    val pairsRDD = su.parallelize(list).flatMap(_.split(" ")).map((_, 1))
    val retRDD = pairsRDD.aggregateByKey(0)(_+_, _+_)
    //        retRDD.foreach(println)
    //        retRDD.saveAsTextFile("E:/data/spark/core/tt")
    /**
      *
      */
    //          retRDD.saveAsHadoopFile()

    retRDD.saveAsNewAPIHadoopFile("hdfs://ns1/output/spark/out",
      classOf[Text],
      classOf[IntWritable],
      classOf[TextOutputFormat[Text, IntWritable]])
  }

  /**saveAsTextFile读存操作
    * countByKey返回键值对
    * @param sc
    */
  def actionOps3(sc: SparkContext): Unit = {
    val list = List("guo zhao yang", "jie zhang", "de zhao chun yu")
    val pairsRDD = sc.parallelize(list).flatMap(_.split(" ")).map((_, 1))
    val retRDD = pairsRDD.aggregateByKey(0)(_+_, _+_)
    val countMap = retRDD.countByKey()//计算当前的key的个数
    for ((k, v) <- countMap) {
      println(k + "--->" + v)
    }
  }

}
