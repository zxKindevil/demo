package com.mycollection

/**
  * @see scala.collection.Traversable
  */
object TraversableDemo1 {
  val list = List(1, 2, 3, 4, 5, 6)

  def main(args: Array[String]): Unit = {
    fold
  }

  def fold: Unit = {
    println((4 /: list) ((x, y) => x + y))
    println((list :\ 1) ((x, y) => x + y))
    list.foldLeft(1)((x, y) => x + y)

    println(list reduceRight ((x, y) => x * y + 1))


  }

  def toArray() = {
    println(Set(1, 2, 3).toList)

    val arry = new Array[Int](3)
    List(1, 2, 3).copyToArray(arry) //scala.collection.IterableLike.copyToArray
    println(arry)
    arry.foreach(x => print(f"$x "))

    println(list span (x => x <= 5))

    println(list partition (_ > 4))

    println(list groupBy (_ % 3))


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
