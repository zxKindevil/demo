package com.mytrait

/**
  * trait is similar to Java 8’s interfaces.
  * @author zhangxin
  *         Created on 18/3/1.
  */
object TraitDemo {
  def main(args: Array[String]) {
    val iterator = new IntIterator(10)
    println(iterator.next())  // returns 0
    println(iterator.next())  // returns 1
  }
}

class IntIterator(to: Int) extends Iterator[Int] {
  private var current = 0

  override def hasNext: Boolean = current < to

  override def next(): Int = {
    if (hasNext) {
      val t = current
      current += 1
      t
    } else 0
  }
}

trait Iterator[A] {
  def hasNext: Boolean

  def next(): A
}

