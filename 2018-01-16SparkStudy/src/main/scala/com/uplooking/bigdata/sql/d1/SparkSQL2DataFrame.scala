package com.uplooking.bigdata.sql.d1

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{Column,DataFrame, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}


/**
  * @ Title: 2018-01-16SparkStudy
  * @ Package:com.uplooking.bigdata.sql.d1
  * @ description: SparkSQL的操作
  * @ This program is protected by copyright laws
  * @ author zhangjie
  * @ date 2018/1/24 
  * @ version V1.0
  */
object SparkSQL2DataFrame {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.apache.hadoop").setLevel(Level.WARN)
    Logger.getLogger("org.spark_project").setLevel(Level.WARN)
    val conf = new SparkConf().setAppName(s"${SparkSQL2DataFrame.getClass.getSimpleName}").setMaster("local[2]")
      .set("spark.shuffle.memoryFraction", "0.3")
      .set("spark.storage.memoryFraction", "0.5")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    //载入外部数据
         val df:DataFrame= sqlContext.read.json("D:/work/test/student.json")
    //打印DF中所有的记录
    df.show
    //打印出DF中所有的schema信息
    df.printSchema()
    //查询出name的列并打印出来
    println("查询出所有的姓名(展示3个)>>>>>>>>>>")
    df.select("name").show(3)
    println("查询出所有的(起别名)姓名(展示5个)>>>>>>>>>>")
    df.select(new Column("name").as("col_name")).show(5)
    println("打印出年龄超过16岁的人>>>>>>>>>>")
    df.select("name", "age", "achievement").where(new Column("age").>=(16)).show()
    println("按成绩进行分组>>>>>>>>>>")
    df.groupBy(new Column("achievement")).count().show()
    println("给指定的列进行计算>>>>>>>>>>")
    df.select(new Column("age").as("现在的年龄"), new Column("age").+(10).as("10年之后的岁数")).show()


    //构建要操作的临时表,用标准的SQL语句完成上述的操作
    df.registerTempTable("student")
    println("使用的SQL完成相同操作>>>>>>>>>")
    val retDF = sqlContext.sql("SELECT achievement, count(1) AS count FROM student GROUP BY achievement ORDER BY count DESC")
    retDF.show()

    sc.stop()

  }

}
