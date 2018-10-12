package com.uplooking.bigdata.core.d4

import com.uplooking.bigdata.util.SparkUtil
import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel

import scala.collection.mutable.ArrayBuffer
import scala.reflect.ClassTag
import scala.util.Random


/**
  * @ Title: 2018-01-16SparkStudy
  * @ Package:com.uplooking.bigdata.core.d4
  * @ description: 数据倾斜问题的五种解决方案
  * @ This program is protected by copyright laws
  * @ author zhangjie
  * @ date 2018/1/23 
  * @ version V1.0
  */
object SparkDataSkew {


  def main(args: Array[String]): Unit = {

    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.apache.hadoop").setLevel(Level.WARN)
    Logger.getLogger("org.spark-project").setLevel(Level.WARN)
    val conf = SparkUtil.getSparkConf(s"${SparkDataSkew.getClass.getSimpleName}", "local[2]")
    val   sc=new SparkContext(conf)
    //假数据
     val  list=List(
       "love love love love",
       "you you I I I",
       "every day"
     )

       // dataStaticFilter(sc,list)
        // dataDynamicFilter(sc,list)
        //dataPolymerizationFilter(sc,list)
        //reduceJoinMapFilter(sc)
         splitJoinFilter(sc)


        sc.stop()

  }

  /**
    * ①静态过滤数据
    * 过滤掉几个没用的key
    * @param sc
    * @param list
    */
  def dataStaticFilter(sc: SparkContext, list: List[String]): Unit = {
          val    listrdd= sc.parallelize(list)
               val  filterList= List("love")
            val  filterBc=sc.broadcast(filterList)
       listrdd.flatMap(_.split(" ")).filter{case word => {
         !filterBc.value.contains(word)
       }
       }.map((_,1)).reduceByKey(_+_).foreach(println)
  }

  /**
    * ②动态过滤数据
    *  每次都到最多的key进行过滤
    * @param sc
    * @param list
    */
  def dataDynamicFilter(sc: SparkContext, list: List[String]): Unit = {
           val  listrdd=sc.parallelize(list)
            val wordRdd:RDD[String]=listrdd.flatMap(_.split(" "))
            val sampleRdd=wordRdd.sample(true,0.5).map((_,1)).reduceByKey(_+_)//抽样数据
               //sampleRdd.foreach(println)
            //对找到的key进行过滤
            val filterKey = sampleRdd.map(t => (t._2, t._1)).sortByKey(false, 1).first()._2
           println("发生数据倾斜的key >>>>"+filterKey)
           val retRdd = wordRdd.filter(word => {
                     !word.equals(filterKey)
                }).coalesce(1).map((_, 1)).reduceByKey(_+_, 10)
                   retRdd.foreach(println)
  }

  /**
    * ③通过两次聚合进行数据过滤
    * @param sc
    * @param list
    */
  def dataPolymerizationFilter(sc: SparkContext, list: List[String]): Unit = {
    //找到key最多的数据
    val  listrdd=sc.parallelize(list)
    val wordRdd:RDD[String]=listrdd.flatMap(_.split(" "))
     //持久化操作(内存)
       wordRdd.persist(StorageLevel.MEMORY_ONLY)
    val sampleRdd=wordRdd.sample(true,0.5).map((_,1)).reduceByKey(_+_)//抽样数据
    //倾斜的数据key
    val filterKey = sampleRdd.map(t => (t._2, t._1)).sortByKey(false, 1).first()._2
     println("发生数据倾斜的key >>>>"+filterKey)
       val prefixRdd:RDD[(String,Int)]=wordRdd.map{case  word=>{
         if (word.equalsIgnoreCase(filterKey)){
           val random = new Random()
           (random.nextInt(2) + "_" + word, 1)
         }else{
           (word, 1)
         }
       }}
     println("添加2以内的随机前缀的输出结果 >>>>")
      prefixRdd.foreach(println)
    //进行局部聚合
       val   part:RDD[(String,Int)]= prefixRdd.reduceByKey(_+_)
    println("输出局部聚合后的结果 >>>>")
    part.foreach(println)
    //进行全局聚合
       val whole:RDD[(String,Int)]=part.map{case (word,count) =>{
         if (word.contains("_")){
         val  fileds=word.split("_")
           (fileds(1),count)
         }else{
           (word,count)
         }
        }}.reduceByKey(_+_)
        println("输出全局聚合后的结果 >>>>")
        whole.foreach(println)
  }

  /**
    * ④Reduce join map
    * 只使用与一张大表一张小表
    * 小表进行广播,大表进行map操作
    * @param sc
    */
  def reduceJoinMapFilter(sc: SparkContext): Unit ={
      val studen=List(
        "001,赵飞燕,1583-05-20,0,河北,华北",
        "002,爱新觉罗玄烨,1783-02-17,1,北京,华北",
        "003,妲己,2002-09-08,0,河南,华中"
      )
       val studentRdd=sc.parallelize(studen)
       val gender=List("0 女", "1 男")
       val genderRdd=sc.parallelize(gender)
       val   studentAndgenderRdd=studentRdd.map{case line=>{
          val fields = line.split(",")
          val genderCode = fields(3).trim
          (genderCode, fields(0) + "\t" + fields(1) + "\t" + fields(2) + "\t" +  fields(4) + "\t" + fields(5))
        }}
         val gendercodeRdd=genderRdd.map{case line =>{
           val fields = line.split(" ")
           (fields(0), fields(1))
         }}
        //将小表进行广播操作
        val map:Map [String,String] =gendercodeRdd.collect().toMap//放到dirver上面
        val genderaBc=sc.broadcast(map)//广播
         //大表进行map操作
          val  studentcodeRdd=studentAndgenderRdd.map{case (gendercode,studentcode) =>{
              val genderMap=genderaBc.value
              val genderValue=genderMap.getOrElse(gendercode,"男")
               studentcode+ "\t" + genderValue
          }}
        studentcodeRdd.foreach(println)
  }

  /**
    * ⑤两张大表,倾斜解决方案
    * @param sc
    */
     def  splitJoinFilter (sc:SparkContext):Unit ={
       val list1 = List(
         "zhang zhang zhang jie",
         "how are you",
         "I am fine",
         "Thank you and you"
       )

       val list2 = List(
         "I am fie too",
         "Thank you",
         "I love you",
         "zhang jie"
       )

       val rdd1:RDD[(String, Int)] = sc.parallelize(list1).flatMap(_.split(" ")).map((_, 1))
       val rdd2:RDD[(String, Int)] = sc.parallelize(list2).flatMap(_.split(" ")).map((_, 1))
       //假如在做rdd1和rdd2的join过程中发生数据倾斜了，我们地位发现是rdd1中的数据操作

         //提取发生倾斜的key
       val dsKey = rdd1.sample(false, 0.6).reduceByKey(_+_).sortBy(t => t._2, true, 1)(new Ordering[Int] {
         override def compare(x: Int, y: Int): Int = {
           y - x
         }
       }, ClassTag.Int.asInstanceOf[ClassTag[Int]]).first()._1

       println("发生数据倾斜的key为 >>>>" + dsKey)

         //拆分数据倾斜的key
       val dsRDD1:RDD[(String, Int)] = rdd1.filter{case (word, count) => {
         word.equalsIgnoreCase(dsKey)
       }}
       println("rdd1被拆分成数据倾斜的rdd为 >>>>")
       dsRDD1.foreach(println)
       println("------------------------------------------------------------------")
       val commonRDD1:RDD[(String, Int)] = rdd1.filter{case (word, count) => {
         !word.equalsIgnoreCase(dsKey)
       }}
       println("rdd1被拆分成正常的rdd为 >>>>")
       commonRDD1.foreach(println)
       println("------------------------------------------------------------------")
       val dsRDD2:RDD[(String, Int)] = rdd2.filter{case (word, count) => {
         word.equalsIgnoreCase(dsKey)
       }}
       println("rdd2被拆分成数据倾斜的rdd为 >>>>")
       dsRDD2.foreach(println)
       println("------------------------------------------------------------------")
       val commonRDD2:RDD[(String, Int)] = rdd2.filter{case (word, count) => {
         !word.equalsIgnoreCase(dsKey)
       }}
       println("rdd2被拆分成正常的rdd为 >>>>")
       commonRDD2.foreach(println)
       println("------------------------------------------------------------------")

         //对发生倾斜的数据添加随机前缀,核对rdd2进行扩容
       val prefixDsRDD1:RDD[(String, Int)] = dsRDD1.map{case (word, count) => {
         val random = new Random()
         (random.nextInt(2) + "_" + word, count)
       }}
       println("倾斜数据rdd1添加随机前缀之后的rdd为 >>>>>")
       prefixDsRDD1.foreach(println)
       println("------------------------------------------------------------------")
       val prefixDsRDD2:RDD[(String, Int)] = dsRDD2.flatMap{case (word, count) => {
         val ab = ArrayBuffer[(String, Int)]()
         for (i <- 0 until 2)  {
           ab.append((i + "_" + word, count))
         }
         ab
       }}
       println("倾斜数据rdd2扩容之后的rdd为 >>>>>")
       prefixDsRDD2.foreach(println)
       println("------------------------------------------------------------------")

         //对有随机前缀的rdd进行join操作和没有随机前缀的rdd进行join操作

       val prefixDsRDD:RDD[(String, (Int, Int))] = prefixDsRDD1.join(prefixDsRDD2)
       val commonJoinRDD:RDD[(String, (Int, Int))] = commonRDD1.join(commonRDD2)
       //去掉随机前缀
       val dsRDD = prefixDsRDD.map{case (word, (count1, count2)) => {
         (word.split("_")(1), (count1, count2))
       }}
       println("有随机前缀join之后的结果为 >>>>")
       prefixDsRDD.foreach(println)
       println("------------------------------------------------------------------")
       //合并数据
       val finalJoinRDD = dsRDD.union(commonJoinRDD)
       println("合并后的数据为 >>>>")
       finalJoinRDD.foreach(println)
       println("------------------------------------------------------------------")
     }
}
