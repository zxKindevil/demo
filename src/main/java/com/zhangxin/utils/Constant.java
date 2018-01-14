package com.zhangxin.utils;

import java.text.SimpleDateFormat;

/**
 * @author zhangxin on 2018/1/14.
 */
public interface Constant {
    //=========eth
    String ETH_SELL_PAGE = "https://otcbtc.com/sell_offers?currency=eth&fiat_currency=cny&payment_type=all";
    String ETH_BUY_PAGE = "https://otcbtc.com/buy_offers?currency=eth&fiat_currency=cny&payment_type=all";
    String ORDER_NOTIFYS = "https://otcbtc.com/account/notifications";


    SimpleDateFormat YYMMDD = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
}
