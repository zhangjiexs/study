package com.uplooking.bigdata.core.d2

import com.uplooking.bigdata.util.SparkUtil
import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

import scala.reflect.ClassTag


/**
  * @ Title: 2018-01-16SparkStudy
  * @ Package:com.uplooking.bigdata.core.d2
  * @ description: 	Transformations：转换(Transformations)
  * (如：map, filter, groupBy, join等)，Transformations操作是Lazy的，
  * 也就是说从一个RDD转换生成另一个RDD的操作不是马上执行，
  * Spark在遇到Transformations操作时只会记录需要
  * 这样的操作，并不会去执行，需要等到有Actions操作的时候才会真正启动计算过程进行计算。
  * @ This program is protected by copyright laws
  * @ author zhangjie
  * @ date 2018/1/16 
  * @ version V1.0
  */
object ScalaSparkTransformationOps01 {
  /**
    *关于transformation的操作
    * @param args
    */
  def main(args: Array[String]): Unit = {
    //设置日志的级别
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.apache.hadoop").setLevel(Level.WARN)
    val su=SparkUtil.getSparkContext(s"${ScalaSparkTransformationOps01.getClass.getCanonicalName}", "local[2]")
    val list = List(1, 2, 3, 4, 5)

      //      transformationMap(su, list) //map：将集合中每个元素乘以9
    //        transformationFilter(su, list) //filter：过滤出集合中的奇数
    //        transformationFlatMap(su)  //flatMap：将行拆分为单词
    //        transformationSample(su)   //sample：根据给定的随机种子seed，随机抽样出数量为frac的数据
    //        transformationUnion(su)  // union：返回一个新的数据集，由原数据集和参数联合而成
    //        transformationGBK(su)  //   groupByKey：对数组进行 group by key操作
    //        transformationRBK(su)  //    reduceByKey：统计每个班级的人数
          transformationJoin(su) //   join：打印关联的组合信息
    //transformationSBK(su) // sortByKey：将学生身高进行排序


    su.stop()
  }

  /**
    *1、map：将集合中每个元素乘以9
    * @param su
    * @param list
    */

  def transformationMap(su: SparkContext, list:List[Int]): Unit = {
    val listRDD: RDD[Int] = su.parallelize(list)
    listRDD.map(_ * 9).foreach(println)
  }

  /**
    *2、filter：过滤出集合中的奇数
    * @param su
    * @param list
    */

  def transformationFilter(su: SparkContext, list:List[Int]): Unit = {
    val listRDD: RDD[Int] = su.parallelize(list)
    listRDD.filter(num => num % 2 != 0).foreach(println)
  }

  /**
    *3、flatMap：将行拆分为单词
    * @param su
    */
  def transformationFlatMap(su: SparkContext): Unit = {
    val list = List("guo xu dong yang fan", "liu liu liu double liu", "old zhang")
    val listRDD:RDD[String] = su.parallelize(list)
    val wordsRDD:RDD[String] = listRDD.flatMap(line => line.split(" "))
    wordsRDD.foreach(println)
  }

  /**
    *4、sample：根据给定的随机种子seed，随机抽样出数量为frac的数据
    * @param su
    */
  def transformationSample(su: SparkContext): Unit = {
    val list = 1 to 10000
    val listRDD = su.parallelize(list)
    val sampleRDD = listRDD.sample(false, 0.02)
    println("sampleRDD's size: " + sampleRDD.count())
    sampleRDD.foreach(println)
  }

  /**
    *5、union：返回一个新的数据集，由原数据集和参数联合而成
    * @param su
    */


  def transformationUnion(su: SparkContext): Unit = {
    val list1 = List(1, 2, 3, 4, 5)
    val list2 = List(5, 6, 7, 8, 9)
    val list1RDD = su.parallelize(list1)
    val list2RDD = su.parallelize(list2)

    val unionRDD = list1RDD.union(list2RDD)
    unionRDD.foreach(println)
  }

  /**
    * 6、groupByKey：对数组进行 group by key操作
     * @param su
    */
  def transformationGBK(su: SparkContext): Unit = {
    val list = List("guo zhao yang", "jie zhang", "de zhao chun yu")
    val pairsRDD = su.parallelize(list).flatMap(_.split(" ")).map((_, 1))
    println("*****************map*****************")
    pairsRDD.foreach(println)
    val gbkRDD:RDD[(String, Iterable[Int])] = pairsRDD.groupByKey()
    println("*****************groupByKey*****************")
    gbkRDD.foreach(println)
  }

  /**
    *、7、reduceByKey：统计每个班级的人数
    * @param su
    */
  def transformationRBK(su: SparkContext): Unit = {
    val list = List("ting zhang zhang", "jie zhang", "de lin hu jie")
    val pairsRDD = su.parallelize(list).flatMap(_.split(" ")).map((_, 1))
    println("*****************map*****************")
    pairsRDD.foreach(println)
    val rbkRDD:RDD[(String, Int)] = pairsRDD.reduceByKey(_+_)
    println("*****************reduceByKey*****************")
    rbkRDD.foreach(println)
  }

  /**
    * 8、join：打印关联的组合信息
    * @param su
    */

  def transformationJoin(su: SparkContext): Unit = {
    val stu = List(
      "001,王小妹,29,0",
      "002,张三,18,1",
      "003,李四,39,1",
      "004,赵飞燕,27,0"
    )
    val score = List(
      "001|97|95|93",
      "003|60|89|59",
      "004|79|95|83"
    )
    val stuListRDD:RDD[String] = su.parallelize(stu)
    val scoreListRDD:RDD[String] = su.parallelize(score)

    val sid2InfoRDD:RDD[(String, String)] = stuListRDD.map(line => {
      val fields = line.split(",")
      val gender = if(fields(3).equalsIgnoreCase("0")) "美女" else "帅锅"
      (fields(0), fields(1) + "\t" + fields(2) + "\t" + gender)
    })

    val sid2ScoreRDD:RDD[(String, String)] = scoreListRDD.map(line => {
      val fields = line.split("\\|")
      (fields(0), fields(1) + "\t" + fields(2) + "\t" + fields(3))
    })
    //查询所有信息，只需要做两张表的关联即可
    println("--------------------^_^-------------------")
    val joinRDD:RDD[(String, (String, String))] = sid2InfoRDD.join(sid2ScoreRDD)
    joinRDD.foreach{case (sid, (info, scoreStr)) => {
      println(s"${sid}\t${info}\t${scoreStr}")
    }}
    println("--------------------~_~-------------------")
    //左外连接
    val leftJoinRDD:RDD[(String, (String, Option[String]))] = sid2InfoRDD.leftOuterJoin(sid2ScoreRDD)

    leftJoinRDD.foreach{case (sid, (stuInfo, option)) => {
      println(s"${sid}\t${stuInfo}\t${option}")
    }}
    println("--------------------$_$-------------------")
    val rightJoinRDD:RDD[(String, (Option[String], String))] = sid2InfoRDD.rightOuterJoin(sid2ScoreRDD)
    rightJoinRDD.foreach{case (sid, (stuOption, scoreStr)) => {
      println(s"${sid}\t${stuOption}\t${scoreStr}")
    }}
  }

  /**
    * 9、sortByKey：将学生身高进行排序
    * @param su
    */
  def transformationSBK(su: SparkContext): Unit = {
    val stu = List(
      "001,王小妹,29,160.8",
      "002,张三,18,172",
      "003,李四,39,180.0",
      "004,赵飞燕,27,155.2"
    )
    val stuRDD = su.parallelize(stu)

    /**
      * sortByKey
      */
    val height2InfoRDD:RDD[(Double, String)] = stuRDD.map{case line => {
      val fields = line.split(",")
      (fields(3).toDouble, line)
    }}
    println("*****************sortByKey*****************")
    height2InfoRDD.sortByKey(false, numPartitions = 1).foreach(println)
    println("*****************sortBy*****************")
    val sortedRDD:RDD[String] = stuRDD.sortBy(line => {
      line.split(",")(3).trim.toDouble
    },
      false,
      1)(new Ordering[Double]{
      override def compare(height1: Double, height2: Double): Int = {
        height1.compareTo(height2)
      }
    }, ClassTag.Double.asInstanceOf[ClassTag[Double]])

    sortedRDD.foreach{case line => println(line)}
  }
}
