//package com.mytrait
//
///**
//  * 协变泛型
//  *
//  * @author zhangxin
//  *         Created on 18/3/1.
//  */
//object TraitDemo4 {
//
//  def main(args: Array[String]) {
//    val buf = newIntSeqBuf(7, 8)
//    println("length = " + buf.length)
//    println("content = " + buf.element)
//  }
//
//  def newIntSeqBuf(e1: Int, e2: Int): SeqBuffer[Int, Seq[Int]] =
//    new SeqBuffer[Int, List[Int]] {
//      val element = List(e1, e2)
//    }
//}
//
//abstract class Buffer[+T] {
//  //换成T,没有+号就不能用List[Int]
//  val element: T
//}
//
//abstract class SeqBuffer[U, +T <: Seq[U]] extends Buffer[T] {
//  def length = element.length
//}
//
//
//
