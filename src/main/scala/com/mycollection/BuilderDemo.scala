package com.mycollection

import scala.collection.BitSet
import scala.collection.generic.CanBuildFrom

object BuilderDemo {
  def main(args: Array[String]): Unit = {
    val myBuilder = new CanBuildFrom[Set[Int], Int, BitSet] {
      override def apply(from: Set[Int]) = apply()

      override def apply() = BitSet.newBuilder
    }

    println(Set(1, 2, 3).map(_ + 1)(myBuilder).getClass)


  }
}

