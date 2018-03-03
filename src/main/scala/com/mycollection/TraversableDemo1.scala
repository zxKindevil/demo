package com.mycollection

object TraversableDemo1 {
  def main(args: Array[String]): Unit = {
  }

  /**
    * Map操作有map，flatMap和collect，它们可以通过对容器中的元素进行某些运算来生成一个新的容器。
    */
  def map(): Unit = {
    val inclusive = 1 to 10
    println(inclusive.getClass)

    println((1 to 10) map (_ + 1))

    println((1 to 10) flatMap (x => Set(x, x + 4)))

    println("Step 1: How to initialize a Sequence which contains donut names and prices")
    val donutNamesandPrices: Seq[Any] = Seq("Plain Donut", 1.5, "Strawberry Donut", 2.0, "Glazed Donut", 2.5)
    println(s"Elements of donutNamesAndPrices = $donutNamesandPrices")

    println("\nStep 2: How to use collect function to cherry pick all the donut names")
    val donutNames: Seq[String] = donutNamesandPrices.collect { case name: String => name }
    println(s"Elements of donutNames = $donutNames")

    println("\nStep 3: How to use collect function to cherry pick all the donut prices")
    val donutPrices: Seq[Double] = donutNamesandPrices.collect { case price: Double => price }
    println(s"Elements of donutPrices = $donutPrices")


  }


  /** ++
    * CanBuildFrom coll builder
    */
  def testplusplush() = {
    val list = List(1, 2, 3)

    val ret = list ++ (4 to 10)

    println(ret)
  }


}
