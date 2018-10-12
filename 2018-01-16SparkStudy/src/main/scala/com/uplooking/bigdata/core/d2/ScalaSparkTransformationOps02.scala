package com.uplooking.bigdata.core.d2

import com.uplooking.bigdata.util.SparkUtil
import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

import scala.collection.mutable.ArrayBuffer

/**
  * @ Title: 2018-01-16SparkStudy
  * @ Package:com.uplooking.bigdata.core.d2
  * @ description:  aggregateByKey和combineByKey区别操作来模拟groupByKey和reduceByKey
  * @ This program is protected by copyright laws
  * @ author zhangjie
  * @ date 2018/1/16 
  * @ version V1.0
  */
object ScalaSparkTransformationOps02 {


  def main(args: Array[String]): Unit = {
    //设置日志级别
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.apache.hadoop").setLevel(Level.WARN)

    val su=SparkUtil.getSparkContext(s"${ScalaSparkTransformationOps02.getClass.getCanonicalName}", "local[2]")
    val list = List(1, 2, 3, 4, 5)


       aggreagateByKey2GBK(su)
     //      aggreagateByKey2RBK(su)
      //    combineByKey2GBK(su)
       //    combineByKey2RBK(su)
    su.stop()

  }

  /**
    * groupByKey:对数组进行 group by key操作;aggregateByKey
    * @param su
    */
  def aggreagateByKey2GBK(su: SparkContext): Unit = {
    val list = List("guo zhao yang", "jie zhang", "de zhao chun yu")
    val pairsRDD = su.parallelize(list).flatMap(_.split(" ")).map((_, 1))
    println("*****************map*****************")
    pairsRDD.foreach(println)
    val gbkRDD:RDD[(String, Iterable[Int])] = pairsRDD.groupByKey()
    println("*****************groupByKey*****************")
    gbkRDD.foreach(println)
    println("*****************aggregateByKey2GroupByKey*****************")
    val abk2GBKRDD = pairsRDD.aggregateByKey(ArrayBuffer[Int]())(
      (partition:ArrayBuffer[Int], num:Int) => {
        partition.append(num)
        partition
      },
      (p1:ArrayBuffer[Int], p2:ArrayBuffer[Int]) => {
        p1.++:(p2)
      }
    )
    abk2GBKRDD.foreach(println)
  }

  /**
    *reduceByKey：统计每个班级的人数;combineByKey
    * @param su
    */
  def aggreagateByKey2RBK(su: SparkContext): Unit = {
    val list = List("ting zhang zhang", "jie zhang", "de lin hu jie")
    val pairsRDD = su.parallelize(list).flatMap(_.split(" ")).map((_, 1))
    println("*****************map*****************")
    pairsRDD.foreach(println)
    val rbkRDD: RDD[(String, Int)] = pairsRDD.reduceByKey(_+_)
    println("*****************reduceByKey*****************")
    rbkRDD.foreach(println)
    println("*****************aggregateByKey2ReduceByKey*****************")
    val aggregatebk2RBKRDD = pairsRDD.aggregateByKey(0)((mergeValue:Int, num:Int) => mergeValue + num,
      (merge1V:Int, merge2V:Int) => merge1V + merge2V)
    aggregatebk2RBKRDD.foreach(println)
  }

  /**
    *groupByKey:对数组进行 group by key操作;combineByKey
    * @param su
    */
  def combineByKey2GBK(su: SparkContext): Unit = {
    val list = List("ting zhang zhang", "jie zhang", "de lin hu jie")
    val pairsRDD = su.parallelize(list).flatMap(_.split(" ")).map((_, 1))
    println("*****************map*****************")
    pairsRDD.foreach(println)
    val gbkRDD:RDD[(String, Iterable[Int])] = pairsRDD.groupByKey()
    println("*****************groupByKey*****************")
    gbkRDD.foreach(println)
    println("*****************combineByKey2GroupByKey*****************")
    val cbk2GBKRDD = pairsRDD.combineByKey(createCombiner, mergeValue, mergeCombiners)
    cbk2GBKRDD.foreach(println)
  }

  /**
    *reduceByKey：统计每个班级的人数;aggregateByKey
    * @param su
    */
  def combineByKey2RBK(su: SparkContext): Unit = {
    val list = List("ting zhang zhang", "jie zhang", "de lin hu jie")
    val pairsRDD = su.parallelize(list).flatMap(_.split(" ")).map((_, 1))
    println("*****************map*****************")
    pairsRDD.foreach(println)
    val rbkRDD: RDD[(String, Int)] = pairsRDD.reduceByKey(_+_)
    println("*****************reduceByKey*****************")
    rbkRDD.foreach(println)
    println("*****************combineByKey2ReduceByKey*****************")

    def createCombiner(num:Int):Int = num
    def mergeValue(partSum:Int, num:Int):Int = partSum + num
    def mergeCombiners(part1Sum:Int, part2Sum:Int):Int = part1Sum + part2Sum

    val cbk2RBKRDD:RDD[(String, Int)] = pairsRDD.combineByKey(createCombiner, mergeValue, mergeCombiners)
    cbk2RBKRDD.foreach(println)
  }

  /**
    * 对应相同的key，用来存放聚合之后的结果
    * 每一个分区partition，都会针对该分区中的相同的key调用一次该函数
    * @param num
    * @return
    */
  def createCombiner(num:Int):ArrayBuffer[Int] = {
    println("*****************createCombiner*****************")
    ArrayBuffer[Int](num)
  }

  /**
    *分区内部的聚合操作，有集合分区，就调用几次该函数
    * @param ab
    * @param num
    * @return
    */
  def mergeValue(ab:ArrayBuffer[Int], num:Int):ArrayBuffer[Int] = {
    println("*****************mergeValue*****************")
    ab.append(num)
    ab
  }

  /**
    *分区间相同key，不同mergeValue之后的结果之间的聚合
    * @param p1
    * @param p2
    * @return
    */
  def mergeCombiners(p1:ArrayBuffer[Int], p2:ArrayBuffer[Int]):ArrayBuffer[Int] = {
    println("*****************mergeCombiners*****************")
    p1.++:(p2)
  }

}
