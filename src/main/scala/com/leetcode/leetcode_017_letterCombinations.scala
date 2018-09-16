package com.leetcode


/** https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/description/
  * 回溯
  * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
  * *
  * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
  * 示例:
  * *
  * 输入："23"
  * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
  * 说明:
  * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
  */
class leetcode_017_letterCombinations {

}

object Solution_017 {
  val phoneNum: Array[String] = Array[String]("", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz")
  var ret: List[String] = List()

  def letterCombinations(digits: String): List[String] = {
    if (digits.isEmpty) return List()
    ret = List()
    recur(digits, 0, "")
    return ret
  }

  def recur(input: String, deep: Int, cur: String) {
    if (deep == input.size) {
      ret = ret.::(cur)
      return
    }
    val index = input.charAt(deep) - '0'
    val numstr = phoneNum(index)
    for (str <- numstr) {
      recur(input, deep + 1, cur + str)
    }
  }

  def main(args: Array[String]): Unit = {
    letterCombinations("")
    println(ret)
  }
}
