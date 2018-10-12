package com.uplooking.bigdata.core.d3

import java.io.{FileInputStream, FileOutputStream, ObjectInputStream, ObjectOutputStream}

import com.uplooking.bigdata.domain.Person



/**
  * @ Title: 2018-01-16SparkStudy
  * @ Package:com.uplooking.bigdata.core.d3
  * @ description: 持久化，序列化的操作,将java对象保存在磁盘中.
  * @ This program is protected by copyright laws
  * @ author zhangjie
  * @ date 2018/1/17 
  * @ version V1.0
  */
object SerializeableOps {

  def main(args: Array[String]): Unit = {

   //writableObj_1
    //readObj_1
    //        writableObj_2
               readObj_2

  }

  /**
    *
    * 写入文件
    */
  def writableObj_1: Unit = {
    val p1 = new Person("张三", 17)
    val p2 = new Person("李四", 29)
    val p3 = new Person("王五", 20)


    val oos: ObjectOutputStream = new ObjectOutputStream(new FileOutputStream("data/obj.txt"))
    oos.writeObject(p1)
    oos.writeObject(p2)
    oos.writeObject(p3)
    oos.close()
  }

  /**
    *手动加入文件结束标记
    * 读出文件
    */
  def readObj_1: Unit = {
    val ois:ObjectInputStream = new ObjectInputStream(new FileInputStream("data/obj.txt"))

    var obj:Object = null
    while((obj = ois.readObject()) != null) {
      obj match {
        case p:Person => {
        println(p.getName + ">>>>>>>" + p.getAge)
        }
        case _ => println("你好啊")
      }
    }
  }

  /**
    *
    * 写入文件
    */
  def writableObj_2: Unit = {
    val p1 = new Person("张三", 17)
    val p2 = new Person("李四", 29)
    val p3 = new Person("王五", 20)
    val pArray = Array[Person](p1, p2,p3)
    val oos: ObjectOutputStream = new ObjectOutputStream(new FileOutputStream("data/obj.txt"))
    oos.writeObject(pArray)
    oos.close()
  }

  /**
    * 使用数组结束标记
    * 读出文件
    */

  def readObj_2: Unit = {
    val ois:ObjectInputStream = new ObjectInputStream(new FileInputStream("data/obj.txt"))
    val obj = ois.readObject()
    obj match {
      case pArray:Array[Person] => {
        pArray.foreach(p => {
        println(p.getName + ">>>>>>>>" + p.getAge)
        })
      }
      case _ => println("我很好")
    }
  }

}
