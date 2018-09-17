package com.leetcode


class leetcode_148_sortList {

}

object Solution {


  def sortList(head: ListNode): ListNode = {
    var rethead: ListNode = new ListNode(-1)
    rethead.next = head
    quickSort(rethead, head, getLast(head))
    return rethead
  }

  def quickSort(head: ListNode, left: ListNode, right: ListNode): Unit = {
    if (isSuccessor(right, left)) {
      var mid: ListNode = partion(head, left, right)
      quickSort(head, left, mid)
      quickSort(head, mid.next, right)
    }
  }


  def partion(head: ListNode, left: ListNode, right: ListNode): ListNode = {
    var midValue = right._x

    var itlt = left

    var itgt = left
    while (itgt != right) {
      if (itgt._x <= midValue) {
        swap(head, itlt, itgt)
        itlt = itlt.next
      }
      itgt = itgt.next
    }
    swap(head, itlt, right)
    return right
  }

  def swap(head: ListNode, itlt: ListNode, itgt: ListNode): Unit = {
    var pritlt: ListNode = head
    while (pritlt.next != itlt) pritlt = pritlt.next
    var pritgt: ListNode = head
    while (pritgt.next != itgt) pritgt = pritgt.next

    var temp = itlt.next

    itlt.next = itgt.next
    pritgt.next = itlt

    itgt.next = temp
    pritlt.next = itgt
  }

  def getLast(head: ListNode): ListNode = {
    var cur: ListNode = head
    while (cur.next != null) {
      cur = cur.next
    }
    return cur
  }

  def isSuccessor(right: ListNode, left: ListNode): Boolean = {
    if (right == left) return false
    var itleft = left

    while (itleft.next != null) {
      itleft = itleft.next
      if (itleft == right) return true
    }

    return false
  }


  def main(args: Array[String]) {
    val node1: ListNode = new ListNode(1)
    val node2: ListNode = new ListNode(2)
    val node3: ListNode = new ListNode(3)
    val node4: ListNode = new ListNode(4)

    node1.next = node2
    node2.next = node3
    node3.next = node4

    val itleft: ListNode = node2
    val itright: ListNode = node4

    //    println(isSuccessor(itright, itleft))
    //    swap(node1, itleft, itright)
    sortList(node3)
    println(node3)
  }
}

