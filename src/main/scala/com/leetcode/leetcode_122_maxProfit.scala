package com.leetcode

class leetcode_122_maxProfit {

}

object Solution122 {
  def maxProfit(prices: Array[Int]): Int = {
    var max: Int = 0

    var i = 1
    while (i < prices.length) {
      if (prices(i) > prices(i - 1)) {
        max += prices(i) - prices(i - 1)
      }
      i = i + 1
    }

    return max
  }

  def main(args: Array[String]) {
    println(maxProfit(Array(7, 1, 5, 3, 6, 4)))
  }
}
