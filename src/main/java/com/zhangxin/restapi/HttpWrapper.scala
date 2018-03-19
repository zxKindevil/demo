package com.zhangxin.restapi

import java.util
import java.util.concurrent.{ConcurrentHashMap, TimeUnit}

import com.fasterxml.jackson.databind.JsonNode
import com.zhangxin.utils.JsonUtil
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
    val getReq: Request = new Request.Builder().url("https://api.huobipro.com/market/detail/merged?symbol=btcusdt").build()
    val btcjson: String = httpClient.newCall(getReq).execute().body().string()

    println(btcjson)
    val jsonNode: JsonNode = JsonUtil.instance().readTree(btcjson)
    val node: JsonNode = jsonNode.get("tick").get("ask")
    val price: Float = node.get(0).floatValue()

    if(price > 8300)


  }


}

