package com.leetcode

/**
  * https://leetcode-cn.com/problems/reverse-linked-list/description/
  * 反转一个单链表。
  * *
  * 示例:
  * *
  * 输入: 1->2->3->4->5->NULL
  * 输出: 5->4->3->2->1->NULL
  * 进阶:
  * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
  */
class leetcode_206_reverseList {

}

object Solution_206 {
  def reverseList(head: ListNode): ListNode = {
    if (head == null) return head
    var last = head
    while (last.next != null) last = last.next;
    doReverse(head)
    return last
  }

  def doReverse(cur: ListNode): ListNode = {
    if (cur.next != null) {
      doReverse(cur.next).next = cur
      cur.next = null
    }
    return cur
  }

  def main(args: Array[String]): Unit = {
    val l1 = new ListNode(1)
    l1.next = new ListNode(2)
    l1.next.next = new ListNode(3)

    val node = reverseList(l1)
    println(node)
  }
}
