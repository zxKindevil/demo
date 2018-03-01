package com.mytrait

import scala.collection.mutable.ArrayBuffer

/**
  * @author zhangxin
  *         Created on 18/3/1.
  */
object TraitDemo2 {
  def main(args: Array[String]) {
    val dog = new Dog("Harry")
    val cat = new Cat("Sally")

    val animals = ArrayBuffer.empty[Pet]
    animals.append(dog)
    animals.append(cat)
    animals.foreach(pet => println(pet.name))  // Prints Harry Sally
  }
}

trait Pet {
  val name: String
}

class Cat(val name: String) extends Pet
class Dog(val name: String) extends Pet

