package com.zhangxin.restapi.inf

import java.util
import java.util.Date
import java.util.concurrent.TimeUnit
import javax.annotation.Resource

import com.fasterxml.jackson.databind.JsonNode
import com.google.common.collect.Queues
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
  private val deque: util.ArrayDeque[Double] = Queues.newArrayDeque()
  private val initTime: Long = System.currentTimeMillis()

  def deal(): Unit = {
    val jsonNode: JsonNode = restAPI.price()
    val price: Float = jsonNode.get("tick").get("ask").get(0).floatValue()
    deque.addLast(price)

    if (price >= Configs.getDouble("rest.eos.price.max")) NotifyX.send(s"$price max")
    if (price <= Configs.getDouble("rest.eos.price.min")) NotifyX.send(s"$price min")

    if (new Date().getTime - initTime > TimeUnit.SECONDS.toMillis(10)) {
      val first: Double = deque.getFirst
      deque.addLast(price)

      val fenshu: Double = (price - first) / first * 100
      println(s"$price $fenshu%")
    } else {
      println(price)
    }
  }
}
