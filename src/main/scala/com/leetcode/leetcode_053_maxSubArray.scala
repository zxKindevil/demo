package com.leetcode

/**
  * dp(i) = dp(i - 1) + s(i) (dp(i - 1) >= 0)
  * dp(i) = s(i)
  */
class leetcode_053_maxSubArray {

}

object Solution53 {
  def maxSubArray(nums: Array[Int]): Int = {
    val ret = new Array[Int](nums.length)
    ret(0) = nums(0)
    var ans = nums(0)

    var i = 1
    while (i < nums.length) {
      if (ret(i - 1) >= 0) {
        ret(i) = ret(i - 1) + nums(i)
      } else {
        ret(i) = nums(i)
      }
      ans = Math.max(ans, ret(i))
      i = i + 1
    }
    return ans
  }

  def main(args: Array[String]): Unit = {
    println(maxSubArray(Array(-2, 1, -3, 4, -1, 2, 1, -5, 4)))
  }
}
