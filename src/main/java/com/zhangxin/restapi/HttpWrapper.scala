package com.zhangxin.restapi

import java.util
import java.util.concurrent.{ConcurrentHashMap, TimeUnit}

import com.fasterxml.jackson.databind.JsonNode
import com.zhangxin.inf.NotifyX
import com.zhangxin.utils.JsonUtil
import okhttp3.{Cookie, OkHttpClient, Request}
import org.springframework.stereotype.Service

/**
  * @author zhangxin
  *         Created on 18/3/19.
  */
@Service
class HttpWrapper {
  val USER_AGENT: String = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36"

  def dealeos: Unit = {
//    val getReq: Request = new Request.Builder().url("https://api.huobipro.com/market/detail/merged?symbol=eosusdt").build()
//    val respJson: String = HttpWrapper.httpClient.newCall(getReq).execute().body().string()
//
//    val jsonNode: JsonNode = JsonUtil.instance().readTree(respJson)
//    val node: JsonNode = jsonNode.get("tick").get("ask")
//    val price: Float = node.get(0).floatValue()
//
//    println(price)
//    if (price >= HttpWrapper.max) NotifyX.send("max")
//    if (price <= HttpWrapper.min) NotifyX.send("min")
  }
}

object HttpWrapper {

  val httpClient: OkHttpClient = new OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS).readTimeout(5, TimeUnit.SECONDS).build()
  val cookieStore: java.util.Map[String, util.List[Cookie]] = new ConcurrentHashMap[String, util.List[Cookie]]


  private val max: Double = 6.1
  private val min: Double = 6.0

  def main(args: Array[String]): Unit = {
    //    val getReq: Request = new Request.Builder().url("https://api.huobipro.com/market/detail/merged?symbol=btcusdt").build()
    //    val btcjson: String = httpClient.newCall(getReq).execute().body().string()
    //
    //    println(btcjson)
    //    val jsonNode: JsonNode = JsonUtil.instance().readTree(btcjson)
    //    val node: JsonNode = jsonNode.get("tick").get("ask")
    //    val price: Float = node.get(0).floatValue()

//    new HttpWrapper().dealeos
    NotifyX.send("min")
  }


}

