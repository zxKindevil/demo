package com.leetcode

/** https://leetcode-cn.com/problems/permutations/description/
  * 回溯法
  * 给定一个没有重复数字的序列，返回其所有可能的全排列。
  * *
  * 示例:
  * *
  * 输入: [1,2,3]
  * 输出:
  * [
  * [1,2,3],
  * [1,3,2],
  * [2,1,3],
  * [2,3,1],
  * [3,1,2],
  * [3,2,1]
  * ]
  */
class leetcode_046_permute {

}

object Solution {
  var ret: List[List[Int]] = List()

  def permute(nums: Array[Int]): List[List[Int]] = {
    ret = List()
    recur(nums, List(), 0)
    return ret
  }

  def recur(nums: Array[Int], cur: List[Int], deep: Int): Unit = {
    if (cur.lengthCompare(nums.length) == 0) {
      ret = ret.::(cur)
      return
    }
    for (num <- nums) {
      if (!cur.contains(num)) { //重点
        recur(nums, cur.::(num), deep + 1)
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val list = permute(Array())
    println(list)
  }
}