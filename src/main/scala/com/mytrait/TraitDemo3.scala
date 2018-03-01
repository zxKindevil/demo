package com.mytrait

/**
  * 泛型
  *
  * @author zhangxin
  *         Created on 18/3/1.
  */
object TraitDemo3 {
  def main(args: Array[String]) {
    val buf = newIntSeqBuf(7, 8)
    println("length = " + buf.length)
    println("content = " + buf.element)
  }

  def newIntSeqBuf(elem1: Int, elem2: Int): IntSeqBuffer =
    new IntSeqBuffer {
      type T = List[U]
      val element = List(elem1, elem2)
    }
}

trait Buffer {
  type T
  val element: T
}

abstract class SeqBuffer extends Buffer {
  type U
  type T <: Seq[U]

  def length = element.length
}

abstract class IntSeqBuffer extends SeqBuffer {
  type U = Int
}





