package com.myimplicit

/**
  * implicit parameter
  * implicit m为抽象类根据泛型类型自动推导实现
  *
  * @author zhangxin
  *         Created on 18/3/2.
  */
object ImplicitDemo {

  def main(args: Array[String]): Unit = {
    println(sum(List(1, 2, 3))) // uses IntMonoid implicitly
    println(sum(List("a", "b", "c"))) // uses StringMonoid implicitly
  }


  implicit val stringMonoid: Monoid[String] = new Monoid[String] {
    def add(x: String, y: String): String = x concat y

    def unit: String = ""
  }

  implicit val intMonoid: Monoid[Int] = new Monoid[Int] {
    def add(x: Int, y: Int): Int = x + y

    def unit: Int = 0
  }

  def sum[A](xs: List[A])(implicit m: Monoid[A]): A = //implicit m为抽象类根据泛型类型自动推导实现
    if (xs.isEmpty) m.unit
    else m.add(xs.head, sum(xs.tail))


}

abstract class Monoid[A] {
  def add(x: A, y: A): A

  def unit: A
}