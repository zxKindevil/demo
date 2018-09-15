package com.leetcode

/**
  * 两数相加
  *
  * 给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。将两数相加返回一个新的链表。
  * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
  * *
  * 示例：
  * *
  * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
  * 输出：7 -> 0 -> 8
  * 原因：342 + 465 = 807
  * https://leetcode-cn.com/problems/add-two-numbers/description/
  */
class leetcode_002_addtownum {

}

object Solution_002 {

  def addTwoNumbers(l1: ListNode, l2: ListNode): ListNode = {
    var list1 = l1
    var list2 = l2
    var head: ListNode = new ListNode()
    var temp = head
    var jinwei = 0

    while (list1 != null || list2 != null) {
      var v1 = if (list1 == null) 0 else list1._x
      var v2 = if (list2 == null) 0 else list2._x

      temp.next = new ListNode((v1 + v2 + jinwei) % 10)
      jinwei = (v1 + v2 + jinwei) / 10

      temp = temp.next
      if (list1 != null) list1 = list1.next
      if (list2 != null) list2 = list2.next
    }
    if (jinwei > 0) temp.next = new ListNode(1)
    return head.next
  }


  def main(args: Array[String]): Unit = {
    val l1 = new ListNode(1)
    l1.next = new ListNode(2)
    l1.next.next = new ListNode(3)

    val l2 = new ListNode(2)
    val node = addTwoNumbers(l1, l2)
    println(node)
  }
}

class ListNode(var _x: Int = 0) {
  var next: ListNode = null
  var x: Int = _x
}