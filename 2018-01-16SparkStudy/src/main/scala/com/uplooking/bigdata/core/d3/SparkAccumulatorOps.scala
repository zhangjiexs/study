package com.uplooking.bigdata.core.d3

import com.uplooking.bigdata.util.SparkUtil

/**
  * @ Title: 2018-01-16SparkStudy
  * @ Package:com.uplooking.bigdata.core.d3
  * @ description: 累加器
  * @ This program is protected by copyright laws
  * @ author zhangjie
  * @ date 2018/1/17 
  * @ version V1.0
  */
object SparkAccumulatorOps {
  def main(args: Array[String]): Unit = {
    val su=SparkUtil.getSparkContext(s"${SparkAccumulatorOps.getClass.getCanonicalName}", "local[2]")
    val range = 1 to 8888
    val listRDD = su.parallelize(range)
    //构造累加器
    val countAcc = su.accumulator[Int](0)

    val rddCount = listRDD.map(i => {
      if(i % 2 == 0) {
        countAcc.add(1)
      }
      i
    }).count()
    println("rdd中的偶数有多少个：" + countAcc.value)
    println("rdd中的有多少个：" + rddCount)
    su.stop()
  }

}
