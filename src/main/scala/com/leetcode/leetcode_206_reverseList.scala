package com.leetcode

class leetcode_206_reverseList {

}

object Solution {
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
