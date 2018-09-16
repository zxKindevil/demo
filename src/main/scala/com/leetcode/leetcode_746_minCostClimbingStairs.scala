package com.leetcode

/** https://leetcode-cn.com/problems/min-cost-climbing-stairs/description/
  * 动态规划
  *
  * 用dp[i] 表示爬 i 个台阶所需要的成本，所以dp[0]=0，dp[1]=0
  * *
  * 每次爬 i 个楼梯，计算的都是从倒数第一个结束，还是从倒数第二个结束，由此可以总结动态转移方程为
  * *
  * dp[i] = min{dp[i-2]+cost[i-2] , dp[i-1]+cost[i-1]};
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
