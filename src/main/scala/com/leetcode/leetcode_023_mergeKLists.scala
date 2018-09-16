package com.leetcode

/** https://leetcode-cn.com/problems/merge-k-sorted-lists/description/
  * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
  * *
  * 示例:
  * *
  * 输入:
  * [
  * 1->4->5,
  * 1->3->4,
  * 2->6
  * ]
  * 输出: 1->1->2->3->4->4->5->6
  */
class leetcode_023_mergeKLists {

}

object Solution_023 {
  def mergeKLists(lists: Array[ListNode]): ListNode = {
    var last: ListNode = null
    for (list <- lists) {
      last = mergeTwoLists(last, list)
    }
    return last;
  }

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