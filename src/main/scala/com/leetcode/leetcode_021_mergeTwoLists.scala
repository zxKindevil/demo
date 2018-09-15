package com.leetcode

/**
  * https://leetcode-cn.com/problems/merge-two-sorted-lists/description/
  *
  * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
  * *
  * 示例：
  * *
  * 输入：1->2->4, 1->3->4
  * 输出：1->1->2->3->4->4
  */
class leetcode_021_mergeTwoLists {

}

object Solution_021 {
  def mergeTwoLists(l1: ListNode, l2: ListNode): ListNode = {
    var itl1 = l1
    var itl2 = l2
    val head: ListNode = new ListNode(0)
    var cur = head
    while (itl1 != null || itl2 != null) {
      if (itl1 == null) {
        cur.next = new ListNode(itl2._x)
        cur = cur.next
        itl2 = itl2.next
      } else if (itl2 == null) {
        cur.next = new ListNode(itl1._x)
        cur = cur.next
        itl1 = itl1.next
      } else {
        if (itl1._x > itl2._x) {
          cur.next = new ListNode(itl2._x)
          cur = cur.next
          itl2 = itl2.next
        } else {
          cur.next = new ListNode(itl1._x)
          cur = cur.next
          itl1 = itl1.next
        }
      }
    }
    return head.next
  }
}