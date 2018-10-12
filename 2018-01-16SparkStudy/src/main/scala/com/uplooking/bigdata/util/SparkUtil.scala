package com.uplooking.bigdata.util

import org.apache.spark.api.java.JavaSparkContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Spark相关工具类
  */
object SparkUtil {

    def getJavaSparkContext(appName:String, master:String):JavaSparkContext = {
        new JavaSparkContext(getSparkConf(appName, master))
    }
    def getSparkContext(appName:String, master:String):SparkContext = {
        new SparkContext(getSparkConf(appName, master))
    }

    def getSparkConf(appName:String, master:String):SparkConf = {
        new SparkConf().setAppName(appName).setMaster(master)
    }
}
