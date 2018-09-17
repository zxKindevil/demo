package com.leetcode

/**
  * https://leetcode-cn.com/problems/generate-parentheses/description/
  * 回溯法
  */
class leetcode_022_generateParenthesis {

}

object Solution {
  var ret: List[String] = null

  def generateParenthesis(n: Int): List[String] = {
    ret = List()
    recur("(", n, 1, 0)
    return ret
  }

  def recur(cur: String, n: Int, left: Int, right: Int): Unit = {
    if (right == n) {
      ret = ret.+:(cur)
      return
    }
    if (left < n) {
      recur(cur + "(", n, left + 1, right)
    }
    if (right < left) {
      recur(cur + ")", n, left, right + 1)
    }
  }

  def main(args: Array[String]) {
    println(generateParenthesis(3))
  }
}