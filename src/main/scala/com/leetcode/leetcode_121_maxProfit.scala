package com.leetcode

class leetcode_121_maxProfit {

}

object Solution121 {
  def maxProfit(prices: Array[Int]): Int = {
    var max: Int = 0
    var min: Int = Integer.MAX_VALUE

    for (elem <- prices) {
      if (elem > min) {
        max = math.max(max, elem - min)
      }
      if (elem < min) {
        min = elem
      }
    }
    return max
  }

  def main(args: Array[String]) {
    println(maxProfit(Array(7, 1, 5, 3, 6, 4)))
  }
}
