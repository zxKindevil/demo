package com.zhangxin.restapi.inf

import javax.annotation.Resource

import com.fasterxml.jackson.databind.JsonNode
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
  def deal(): Unit = {
    val jsonNode: JsonNode = restAPI.price()
    val price: Float = jsonNode.get("tick").get("ask").get(0).floatValue()

    if (price >= Configs.getDouble("rest.eos.price.max")) NotifyX.send(s"$price max")
    if (price <= Configs.getDouble("rest.eos.price.min")) NotifyX.send(s"$price min")
  }
}
