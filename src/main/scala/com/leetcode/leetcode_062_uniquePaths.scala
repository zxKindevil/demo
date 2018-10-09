package com.leetcode

/**
  * dp[i][j]=dp[i-1][j]+dp[i][j-1]
  */
class leetcode_062_uniquePaths {

}

object Solution62 {
  var count: Int = 0

  def uniquePaths(m: Int, n: Int): Int = {
    val dp = new Array[Array[Int]](m)
    for (i <- 0 until m) {
      dp(i) = new Array[Int](n)
    }

    println(dp(0)(0))

    1
  }

  def main(args: Array[String]) {
    println(uniquePaths(7, 3))
  }
}
