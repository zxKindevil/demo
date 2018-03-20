package com.zhangxin.restapi.inf

import java.util
import java.util.Date
import java.util.concurrent.TimeUnit
import javax.annotation.Resource

import com.fasterxml.jackson.databind.JsonNode
import com.google.common.collect.Queues
import com.google.common.util.concurrent.RateLimiter
import com.zhangxin.biz.Configs
import com.zhangxin.inf.NotifyX
import com.zhangxin.restapi.api.RestAPI
import org.springframework.stereotype.Service

/**
  * @author zhangxin
  *         Created on 18/3/20.
  */
@Service
class PriceNotifyer(@Resource restAPI: RestAPI) {
  private val limiter: RateLimiter = RateLimiter.create(1)
  private val percentLimiter: RateLimiter = RateLimiter.create(1)

  private val deque: util.ArrayDeque[Double] = Queues.newArrayDeque()
  private val initTime: Long = System.currentTimeMillis()

  def deal(): Unit = {
    val jsonNode: JsonNode = restAPI.price()
    val price: Float = jsonNode.get("tick").get("ask").get(0).floatValue()
    deque.addLast(price)

    if (price >= Configs.getDouble("rest.eos.price.max") && limiter.tryAcquire(1)) NotifyX.send(s"$price max")
    if (price <= Configs.getDouble("rest.eos.price.min") && limiter.tryAcquire(1)) NotifyX.send(s"$price min")

    val percent: Double = this.dealPercent(price)

    this.notifyPercenter(percent)

    println(f"$price $percent%.3f")
  }

  def dealPercent(price: Double): Double = {
    if (new Date().getTime - initTime > TimeUnit.SECONDS.toMillis(60)) {
      val first: Double = deque.getFirst

      val percent: Double = (price - first) / first * 100

      percent
    } else 0.0
  }

  def notifyPercenter(percent: Double) = {
    if (Math.abs(percent) > 0.1 && percentLimiter.tryAcquire(1)) {
      NotifyX.send(f"$percent exit percent")
    }
    //    if(percent >= Configs.getDouble("rest"))
  }
}

object PriceNotifyer {
  def main(args: Array[String]) {
    NotifyX.send("test")
  }
}
