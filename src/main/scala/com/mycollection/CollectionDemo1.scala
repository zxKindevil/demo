package com.mycollection

import scala.collection.mutable

/**
  * @author zhangxin
  *         Created on 18/3/2.
  */
object CollectionDemo1 {
  private val list = (1 to 10).toList

  def main(args: Array[String]): Unit = {
    val updated: List[Int] = list.updated(1, 7)

    val ints: mutable.MutableList[Int] = scala.collection.mutable.MutableList(1, 2, 3)

    ints(0) = 1
    println(updated)



    val sorted: List[Int] = updated.sorted
    println(sorted)

    list.reverse

    println(List(1, 2, 3) intersect List(3, 4, 5))

    println(List(1, 2, 3) diff List(3, 4, 5))

    println(List(1, 2, 3) union List(3, 4, 5))

    println(List(1, 2, 3, 3).distinct)

    val user: User = new User("tset", 1)

    user.name = "sdfsdf"
    user.age = 2

  }
}

case class User(var name: String, var age: Int) {

}
