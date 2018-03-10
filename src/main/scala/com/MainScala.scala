package com

/**
  * @author zhangxin
  *         Created on 18/3/1.
  */
object MainScala {
  def main(args: Array[String]): Unit = {
    val arr = Array(1, 9, 3, 4, 5, 6)

    println(twoSum(arr.sorted, 9).toList)
  }

  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
    val sorted = nums.sorted

    for (i <- 1 to 10) {
      for (j <- 2 to 10) {
        println(s"$i $j")
      }
    }

    new Array[Int](1)
  }
}

