package com.uplooking.bigdata.sql.d1

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}
import java.util

import com.uplooking.bigdata.domain.Student

import scala.collection.{JavaConversions, mutable}
import org.apache.spark.sql.types.{DataTypes, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Row, SQLContext}

/**
  * @ Title: 2018-01-16SparkStudy
  * @ Package:com.uplooking.bigdata.sql.d1
  * @ description: SparkRDD的操作
  *
  * ①通过反射的方式构建的DataFrame
  * ②通过动态编程的方式构建的DataFrame
  * @ This program is protected by copyright laws
  * @ author zhangjie
  * @ date 2018/1/24 
  * @ version V1.0
  */
object SparkRDD2DataFrame {

  def main(args: Array[String]): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.apache.hadoop").setLevel(Level.WARN)
    Logger.getLogger("org.spark_project").setLevel(Level.WARN)
    val conf = new SparkConf().setAppName(s"${SparkRDD2DataFrame.getClass.getSimpleName}").setMaster("local[2]")
      .set("spark.shuffle.memoryFraction", "0.3")
      .set("spark.storage.memoryFraction", "0.5")
    val sc = new SparkContext(conf)
    val qc = new SQLContext(sc)
    val list = util.Arrays.asList[Student](
      new Student("张杰", 13, 1, 98),
      new Student("张三", 23, 1, 60),
      new Student("李四", 18, 0, 89),
      new Student("王二", 17, 0, 56),
      new Student("赵六", 18, 1, 47)
    )


    //通过反射方式构建的DataFrame
   // val pDF: DataFrame = createByreflectionDataFrame(sc, qc, list)
    //通过动态编程的方式构建的DataFrame
   // val pDF:DataFrame = createBydynamicDataFrame(sc, qc, list)

    //男生中成绩最高的人和女生选修成绩最低的人
    //pDF.registerTempTable("student")
    val sql = "select name, age, gender, achievement from student p1 " +
      "right join ( " +
      "select max(achievement) achievements from student where gender = 1 " +
      ") t1 " +
      "on t1.achievements = p1.achievement " +
      "where p1.gender = 1 " +
      "union " +
      "select name, age, gender, achievement from student p2 " +
      "right join ( " +
      "select min(achievement) achievements from student where gender = 0 " +
      ") t2 " +
      "on t2.achievements = p2.achievement "+
      "where p2.gender = 0"

    qc.sql(sql).show()
    sc.stop()
  }
  /**
    * 通过反射的方式构建的DataFrame
    * @param sc
    * @param qc
    * @param list
    * @return
    */
  def createByreflectionDataFrame(sc: SparkContext, qc: SQLContext, list:util.ArrayList[Student]): DataFrame = {

    //使用list+反射
    //  val pDF = sqlContext.createDataFrame(list, classOf[Student])
    //通过rdd+反射
    val studentRDD = sc.parallelize(JavaConversions.asScalaBuffer(list))
    val pDF = qc.createDataFrame(studentRDD, classOf[Student])
    pDF
  }

  /**
    *通过动态编程的方式构建的DataFrame
    * @param sc
    * @param qc
    * @param list
    * @return
    */
/*  def createBydynamicDataFrame(sc: SparkContext, qc: SQLContext, list:util.List[Student]): DataFrame = {

    //val rows = sc.parallelize(list).map{case student:Student => {
      //Row(student.getName, student.getAge, student.getGender, student.getAchievement)
    }}

    //List[Row]
    val schema = StructType(List(
      StructField("name", DataTypes.StringType, false),
      StructField("age", DataTypes.IntegerType, false),
      StructField("gender", DataTypes.IntegerType, false),
      StructField("achievement", DataTypes.IntegerType, false)
    ))

    qc.createDataFrame(rows, schema)

  }*/

}
