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
    test()
  }

  def test(): Unit = {
    val file: File = new File("/Users/zhangxin/temp.txt")
    val list: util.List[String] = Files.readLines(file, Charsets.UTF_8)

    val lines: List[String] = list.toList.filter(x => x.startsWith("[2018-03-06"))


    val mutilmap: util.HashMap[String, List[String]] = new util.HashMap[String, List[String]]

    val toList: List[(String, String)] = lines.map(x => {
      val openidRegex = "\\[([a-zA-Z0-9-_]+)\\]".r
      val matches: Iterator[Match] = openidRegex findAllMatchIn x
      val openid: String = matches.toList.apply(1).group(1)

      val name: String = ("patientName\":\"(\\W+)\",".r() findAllMatchIn x).toList.apply(0).group(1)

      var list: List[String] = mutilmap.getOrDefault(openid, List())
      list = list.+:(name)

      mutilmap.put(openid, list)

      (openid, name)
    })

    mutilmap.forEach((x, y) => {
      println((x, y))
    })

  }
}
