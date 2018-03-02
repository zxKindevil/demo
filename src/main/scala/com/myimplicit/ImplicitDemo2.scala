package com.myimplicit

/**
  *
  * implicit class 可以直接用构造函数的参数推导出这个类
  * https://docs.scala-lang.org/overviews/core/implicit-classes.html
  *
  * @author zhangxin
  *         Created on 18/3/2.
  */
object ImplicitDemo2 {
  def main(args: Array[String]) {
    5 times println("hi")
  }

  implicit class IntWithTimes(x: Int) {
    def times[A](f: => A): Unit = {
      def loop(current: Int): Unit =
        if (current > 0) {
          f
          loop(current - 1)
        }
      loop(x)
    }
  }

}
