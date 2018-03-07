package com

import java.io.File
import java.util

import com.fasterxml.jackson.databind.{JsonNode, ObjectMapper}
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
    val jsonNode: JsonNode = new ObjectMapper().readTree("{\"productId\"    :\"110\",\"orderFrom\":\"CHECKUP_MINA\",\"checkupDate\":\"2018-03-09\",\"userInfo\":{\"patientName\":\"苟娇\",\"idCardNo\":\"513701199709222224\",\"phone\":\"15011013389\"},\"_\":1520303280553}")
    println(jsonNode.get("orderFrom").textValue())

    test()
  }

  def test(): Unit = {
    val file: File = new File("/Users/zhangxin/temp.txt")
    val lines: util.List[String] = Files.readLines(file, Charsets.UTF_8)

    lines.toList forEach (x => {
      if (x.startsWith("[2018-03-06")) {
        val openidRegex = "\\[([a-zA-Z0-9-_]+)\\]".r
        val matches: Iterator[Match] = openidRegex findAllMatchIn x
        val openid: String = matches.toList.apply(1).group(1)
        //        println(openid)

        val name: String = ("patientName\":\"(\\W+)\",".r() findAllMatchIn x).toList.apply(0).group(1)
      }
    })
  }
}

