package com.mycollection

/**
  * scala.collection.Iterable
  *
  * @see scala.collection.IterableLike
  */
object IteratbleDemo1 {
  private val list = List(1, 2, 3, 4, 5, 6)

  def main(args: Array[String]): Unit = {
    val iterator = list grouped 2
    iterator.foreach(println)

    println(s"=====")

    val sliding = list sliding 3
    sliding.foreach(println)


  }

}
