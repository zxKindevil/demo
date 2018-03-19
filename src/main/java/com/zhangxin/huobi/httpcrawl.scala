package com.zhangxin.huobi

import org.apache.http.HttpVersion
import org.apache.http.client.fluent.Request
import org.apache.http.entity.ContentType

/**
  * @author zhangxin
  *         Created on 18/3/19.
  */
class httpcrawl {


}

object httpcrawl {
  def main(args: Array[String]) {
    val request: Request = Request.Get("http://www.example.com/")
    val string: String = request
      .connectTimeout(1000)
      .socketTimeout(1000)
      .execute().returnContent().asString()
    //    println(string)

    val string1: String = Request.Post("http://www.example.com/").useExpectContinue().version(HttpVersion.HTTP_1_1).bodyString("Important stuff", ContentType.DEFAULT_TEXT).execute().returnContent().asString()

    //    println(string1)


  }
}
