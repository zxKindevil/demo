package com.zhangxin.biz;

import com.google.common.collect.Maps;
import com.zhangxin.utils.Constant;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.util.Map;

/**
 * @author zhangxin on 2018/1/14.
 */
public class ConnectionHolder {
    public static Connection eth_sell_connection = Jsoup.connect(Constant.ETH_SELL_PAGE);
    public static Connection eth_buy_connection = Jsoup.connect(Constant.ETH_BUY_PAGE);

    public static Map<Key, Connection> connectionMap = Maps.newHashMap();

    static {
        connectionMap.put(Key.ETH_SELL, Jsoup.connect(Constant.ETH_SELL_PAGE));
        connectionMap.put(Key.ETH_BUY, Jsoup.connect(Constant.ETH_BUY_PAGE));
    }

    public enum Key {
        ETH_SELL,
        ETH_BUY,;
    }
}



