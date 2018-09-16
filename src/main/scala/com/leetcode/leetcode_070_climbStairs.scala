package com.leetcode

/** https://leetcode-cn.com/problems/climbing-stairs/description/
  * 直接用 dfs 和递归会超时
  * 动态规划
  */
class leetcode_070_climbStairs {

}

object Solution_070 {
  def climbStairs(n: Int): Int = {
    val ret = Array[Int](1, 1)
    for (i <- 2 to n) {
      val cur = ret(0) + ret(1)
      ret(0) = ret(1)
      ret(1) = cur
    }
    return ret(1)
  }

  //  public int climbStairs(int n) {
  //    if(n == 1 || n<=0) return 1;
  //    return climbStairs(n-1) + climbStairs(n-2);       //Time Limit Exceeded when n >= 42
  //  }

  def main(args: Array[String]): Unit = {
    println(climbStairs(45))
  }
}

object Solution_070_time_out {
  val step: Array[Int] = Array(1, 2)
  var sum: Int = 0

  def climbStairs(n: Int): Int = {
    sum = 0
    dfs(0, n)
    return sum
  }

  def dfs(cur: Int, n: Int): Unit = {
    if (cur == n) {
      sum += 1
      return
    }
    if (cur > n) return
    if (cur < n) {
      for (go <- step) {
        dfs(cur + go, n)
      }
    }
  }
}