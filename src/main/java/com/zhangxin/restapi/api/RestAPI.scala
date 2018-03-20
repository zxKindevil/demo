package com.zhangxin.restapi.api

import com.fasterxml.jackson.databind.JsonNode
import com.zhangxin.restapi.HttpWrapper
import com.zhangxin.utils.JsonUtil
import okhttp3.Request
import org.springframework.stereotype.Service

/**
  * @author zhangxin
  *         Created on 18/3/20.
  */
@Service
class RestAPI {
  def price(): JsonNode = {
    val getReq: Request = new Request.Builder().url("https://api.huobipro.com/market/detail/merged?symbol=eosusdt").build()
    val respJson: String = HttpWrapper.httpClient.newCall(getReq).execute().body().string()

    JsonUtil.instance().readTree(respJson)
  }
}


