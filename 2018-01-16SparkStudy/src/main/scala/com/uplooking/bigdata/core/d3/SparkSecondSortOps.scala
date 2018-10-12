package com.uplooking.bigdata.core.d3

import com.uplooking.bigdata.util.SparkUtil
import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

import scala.reflect.ClassTag

/**
  * @ Title: 2018-01-16SparkStudy
  * @ Package:com.uplooking.bigdata.core.d3
  * @ description: 二次排序
  * @ This program is protected by copyright laws
  * @ author zhangjie
  * @ date 2018/1/17 
  * @ version V1.0
  */
object SparkSecondSortOps {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.apache.hadoop").setLevel(Level.WARN)
    Logger.getLogger("org.spark-project").setLevel(Level.WARN)
    val sc = SparkUtil.getSparkContext(s"${SparkSecondSortOps.getClass.getSimpleName}", "local[2]")
    val lines:RDD[String] = sc.textFile("D:/work/spark/test.csv")
    SparkContext
  }

  /**
    *
    * @param lines
    */
  def secondSortWithSortBy(lines: RDD[String]): Unit = {
    val sortedRDD: RDD[String] = lines.sortBy(line => line, true, 1)(new Ordering[String] {
      override def compare(xLine: String, yLine: String): Int = {
        val xFields = xLine.split(" ")
        val yFields = yLine.split(" ")
        val xFirst = xFields(0).trim.toInt
        val xSecond = xFields(1).trim.toInt
        val yFirst = yFields(0).trim.toInt
        val ySecond = yFields(1).trim.toInt

        var ret = xFirst.compareTo(yFirst)
        if (ret == 0) {
          ret = ySecond.compareTo(xSecond)
        }
        ret
      }
    }, ClassTag.Object.asInstanceOf[ClassManifest[String]])


    sortedRDD.foreach(println)
  }
}
