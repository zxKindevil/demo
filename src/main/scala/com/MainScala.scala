package com

import scala.collection.generic.CanBuildFrom
import scala.collection.{BitSet, mutable}

/**
  * @author zhangxin
  *         Created on 18/3/1.
  */
object MainScala {
  def main(args: Array[String]): Unit = {
    val builder: mutable.SetBuilder[Int, Set[Int]] = new mutable.SetBuilder(Set())
    builder.+=(1)
    builder.+=(2)
    builder.+=(3)

    val result: Set[Int] = builder.result()

    Set(1, 2, 3) map (_ * 2)

    val hashSetBuilder = new CanBuildFrom[Set[Int], Int, mutable.HashSet[Int]] {
      override def apply(from: Set[Int]): mutable.Builder[Int, mutable.HashSet[Int]] = apply()

      override def apply(): mutable.Builder[Int, mutable.HashSet[Int]] = mutable.HashSet.newBuilder[Int]
    }

    println(Set(1, 2, 3).map(_ * 2)(hashSetBuilder).getClass)
  }
}

