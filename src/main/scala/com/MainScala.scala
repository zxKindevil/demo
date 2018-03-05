package com

import scala.collection.generic.CanBuildFrom
import scala.collection.mutable
import scala.collection.mutable.BitSet

/**
  * @author zhangxin
  *         Created on 18/3/1.
  */
object MainScala {
  private val list: List[Int] = List(1, 2, 3, 4)

  def main(args: Array[String]): Unit = {
    val setToBitSetBuilder = new CanBuildFrom[Set[Long], Int, BitSet] {
      def apply(from: Set[Long]) = this.apply();

      def apply() = BitSet.newBuilder

      mutable.HashSet.newBuilder
    }


  }
}

