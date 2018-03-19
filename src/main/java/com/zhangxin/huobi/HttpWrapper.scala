package com.zhangxin.huobi

import java.util
import java.util.concurrent.{ConcurrentHashMap, TimeUnit}

import okhttp3.{Cookie, OkHttpClient, Request}

/**
  * @author zhangxin
  *         Created on 18/3/19.
  */
class HttpWrapper {
  val USER_AGENT: String = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36"

}

object HttpWrapper {
  private val httpClient: OkHttpClient = new OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS).readTimeout(5, TimeUnit.SECONDS).build()
  private val cookieStore: java.util.Map[String, util.List[Cookie]] = new ConcurrentHashMap[String, util.List[Cookie]]

  def main(args: Array[String]): Unit = {
    val getReq: Request = new Request.Builder().url("http://www.example.com").build()
    println(httpClient.newCall(getReq).execute().body().string())
  }

}

