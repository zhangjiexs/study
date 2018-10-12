package com.uplooking.bigdata.core.d3
import com.uplooking.bigdata.util.SparkUtil

/**
  * @ Title: 2018-01-16SparkStudy
  * @ Package:com.uplooking.bigdata.core.d3
  * @ description: Spark关于持久化对象的操作
  * @ This program is protected by copyright laws
  * @ author zhangjie
  * @ date 2018/1/17 
  * @ version V1.0
  */
object SparkPersisitRDDOps {
  val su=SparkUtil.getSparkContext(s"${SparkPersisitRDDOps.getClass.getCanonicalName}", "local[2]")

     var  start=System.currentTimeMillis()
       val  lines= su.textFile("D:/work/spark/test.txt")
          lines.cache()
       val     words=lines.flatMap(_.split(""))
         val     pairs=words.map((_,1))
           val     rets=pairs.reduceByKey(_+_)
            val      count1=rets.count()
  println("count1=" + count1 + "，耗时：" + (System.currentTimeMillis() - start) + "ms")

  /**
    *序列化后的速度
    * 会变快
    */
  start=  System.currentTimeMillis()
  val count2 = lines.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_+_).count()
  println("count2=" + count2 + "，耗时：" + (System.currentTimeMillis() - start) + "ms")

  lines.unpersist()//卸载

    su.stop()




}
