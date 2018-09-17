package com.leetcode


/** https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/
  * 二分查找
  */
class leetcode_034_searchRange {

}

object leetcode_034_searchRange {
  def searchRange(nums: Array[Int], target: Int): Array[Int] = {
    var ret: Array[Int] = Array()
    val index: Int = binSearch(nums, target)
    if (index == -1) return Array(-1, -1)


    var right = index
    while (right <= nums.length) {
      if (right == nums.length || nums(right) != target) {
        ret = ret.+:(right - 1)
        right = nums.length + 1
      }
      right += 1
    }

    var left = index
    while (left >= -1) {
      if (left == -1 || nums(left) != target) {
        ret = ret.+:(left + 1)
        left = -2
      }
      left = left - 1
    }

    ret
  }

  def binSearch(nums: Array[Int], target: Int): Int = {
    var left = 0
    var right = nums.length - 1
    while (left <= right) {
      val mid: Int = (left + right) / 2
      if (nums(mid) > target) {
        right = mid - 1
      }
      else if (nums(mid) < target) {
        left = mid + 1
      }
      else return mid
    }
    return -1
  }

  def main(args: Array[String]) {
    println(searchRange(Array(1), 1).toList)
  }

}


