package com.zhangxin.binance

import java.util.Date
import java.util.concurrent.TimeUnit
import javax.annotation.Resource

import com.google.common.collect.Queues
import com.google.common.util.concurrent.RateLimiter
import com.zhangxin.biz.Configs
import com.zhangxin.restapi.api.NotifyX
import org.springframework.stereotype.Service

/**
  * @author zhangxin
  *         Created on 18/3/20.
  */
@Service
class PriceHandler(@Resource binanceApi: BinanceApi) {
  private val limiter: RateLimiter = RateLimiter.create(0.1)
  private val percentLimiter: RateLimiter = RateLimiter.create(0.2)

  private val deque: java.util.ArrayDeque[Double] = Queues.newArrayDeque()
  private val initTime: Long = System.currentTimeMillis()

  def deal(coin: String): Unit = {
    val price = binanceApi.price(coin)
    deque.addLast(price)

    if (price >= Configs.getDouble(s"binance.$coin.price.max") && limiter.tryAcquire(1)) NotifyX.send(s"$coin $price max")
    if (price <= Configs.getDouble(s"binance.$coin.price.min") && limiter.tryAcquire(1)) NotifyX.send(s"$coin $price min")

    val percent: Double = this.dealPercent(price, coin)

    this.notifyPercenter(percent, coin)

    println(f"$coin $price $percent%.3f")
  }

  def dealPercent(price: Double, coin: String): Double = {
    if (new Date().getTime - initTime > TimeUnit.SECONDS.toMillis(60)) {
      val first: Double = deque.removeFirst()

      val percent: Double = (price - first) / first * 100

      percent
    } else 0.0
  }

  def notifyPercenter(percent: Double, coin: String) = {
    if (Math.abs(percent) > Configs.getDouble(s"binance.$coin.price.max.percent") && percentLimiter.tryAcquire(1)) {
      NotifyX.send(f"$coin $percent exit percent")
    }
    //    if(percent >= Configs.getDouble("rest"))
  }
}

object PriceNotifyer {
  def main(args: Array[String]) {
    val create: RateLimiter = RateLimiter.create(0.1)

    while (true) {
      create.acquire()
      println("out")
    }
  }
}


object PriceHandler {

}
