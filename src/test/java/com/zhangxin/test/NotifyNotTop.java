package com.zhangxin.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangxin on 2018/1/13.
 */
public class NotifyNotTop {
    public static String path = "https://otcbtc.com/sell_offers?currency=eth&fiat_currency=cny&page=1&payment_type=all";
    public static void main(String[] args) throws IOException, InterruptedException {
        while (!Thread.interrupted()) {
            Document document = Jsoup.connect(path)
                    .get();

            Elements remcard = document.getElementsByClass("recommend-card");
            Elements select = remcard.select("a[href^=/users]");
            boolean zxkindevil = select.get(0).text().endsWith("zxkindevil");
            if(!zxkindevil){
                NotifyTest.play();
            }
            System.out.println(select.get(0));
            TimeUnit.SECONDS.sleep(10);
        }

    }
}
