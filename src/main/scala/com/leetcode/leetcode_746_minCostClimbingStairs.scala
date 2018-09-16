package com.leetcode

/** https://leetcode-cn.com/problems/min-cost-climbing-stairs/description/
  * 动态规划
  *
  */
class leetcode_746_minCostClimbingStairs {

}

object Solution746 {
  def minCostClimbingStairs(cost: Array[Int]): Int = {
    val ret: Array[Int] = Array(0, 0)
    for (i <- 2 to cost.length) {
      var cur = Math.min(ret(0) + cost(i - 2), ret(1) + cost(i - 1))
      ret(0) = ret(1)
      ret(1) = cur
    }
    return ret(1)
  }

  def main(args: Array[String]): Unit = {
    println(minCostClimbingStairs(Array(10, 15, 20)))
  }
}
