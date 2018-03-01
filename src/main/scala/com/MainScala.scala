package com

/**
  * @author zhangxin
  *         Created on 18/3/1.
  */
object MainScala {
  def main(args: Array[String]) {
    println("r")
  }
}

trait Iterator[A] {
  def hasNext: Boolean
  def next(): A
}
