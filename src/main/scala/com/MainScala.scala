package com

import java.io.File
import java.util

import com.google.common.base.Charsets
import com.google.common.io.Files

import scala.collection.JavaConversions._
import scala.util.matching.Regex.Match

/**
  * @author zhangxin
  *         Created on 18/3/1.
  */
object MainScala {
  //ohFAc0ZFL4ocY-D0ru625GOC838c
  val pattern = "[a-zA-Z0-9-]+".r

  def main(args: Array[String]): Unit = {
//    println((List(1, 2, 3) map (x => (x + 1, x + 2))).toMap)

        test()
  }

  def test(): Unit = {
    val file: File = new File("/Users/zhangxin/temp.txt")
    val lines: util.List[String] = Files.readLines(file, Charsets.UTF_8)

    println(lines.toList.map(x =>
      if (x.startsWith("[2018-03-06")) {
        val openidRegex = "\\[([a-zA-Z0-9-_]+)\\]".r
        val matches: Iterator[Match] = openidRegex findAllMatchIn x
        val openid: String = matches.toList.apply(1).group(1)
        //        println(openid)

        val name: String = ("patientName\":\"(\\W+)\",".r() findAllMatchIn x).toList.apply(0).group(1)

        return (openid, name)
      }
      else ("a", "b")
    ).toMap)
  }

  def testb(): Unit = {
    //    val list = List("this", "maps", "string", "to", "length") map { s => (s, s.length) }
    //    val list = List("this", "is", "a", "bunch", "of", "strings")
    //    val string2Length = Map(list map { s => (s, s.length) }: _*)
  }
}

