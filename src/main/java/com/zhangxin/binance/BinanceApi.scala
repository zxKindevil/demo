package com.zhangxin.binance

import java.util.concurrent.TimeUnit

import com.zhangxin.utils.JsonUtil
import okhttp3.{OkHttpClient, Request}
import org.springframework.stereotype.Service

@Service
class BinanceApi {

  private val host = "https://api.binance.com"
  private val servertime = "/api/v1/time"
  private val exchangeInfo = "api/v1/exchangeInfo"
  private val depth = "/api/v1/depth?symbol=BTCUSDT&limit=5"
  private val trades = "/api/v1/trades?symbol=EOSBTC&limit=5"
  //最新成交
  private val price = "/api/v3/ticker/price?symbol=%s"
  //价格
  private val klines = host + "/api/v1/klines" //k线

  def price(coin: String): Double = {
    val resp = BinanceApi.httpClient.newCall(new Request.Builder().url(String.format(host + price, coin)).build())
      .execute().body().string()
    JsonUtil.instance().readTree(resp).get("price").asDouble() * 1000
  }

  def klines(coin: String): Double = {
    val resp = BinanceApi.httpClient.newCall(new Request.Builder().url(String.format(host + price, coin)).build())
      .execute().body().string()
    println(resp)
    println(JsonUtil.instance().readTree(resp).get("price"))
    JsonUtil.instance().readTree(resp).get("price").asDouble() * 1000
  }
}

object BinanceApi {
  val httpClient: OkHttpClient = new OkHttpClient.Builder().writeTimeout(5, TimeUnit.SECONDS).connectTimeout(5, TimeUnit.SECONDS).readTimeout(5, TimeUnit.SECONDS).build()


  def main(args: Array[String]): Unit = {
    println(new BinanceApi().price("ONTBTC"))
  }
}
