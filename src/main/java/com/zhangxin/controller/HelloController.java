package com.zhangxin.controller;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.io.Files;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangxin on 2018/1/9.
 */
@RestController
public class HelloController implements InitializingBean {
    public static String BUY = "https://otcbtc.com/sell_offers?currency=eth&fiat_currency=cny&payment_type=all";
    public static String SALE = "https://www.otcbtc.com/buy_offers?currency=eth&fiat_currency=cny&payment_type=all";

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        new Thread(() -> {
            try {
                deal();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void deal() throws IOException, InterruptedException {
        Connection connect = Jsoup.connect(BUY);

        File file = new File("price.txt");

        while (!Thread.interrupted()) {
            BigDecimal finalmin = BigDecimal.ZERO;
            BigDecimal finalshijia;
            BigDecimal finalyijia;

            Document doc = connect.get();
            Elements elements = doc.getElementsByClass("recommend-card__price");
            BigDecimal min = new BigDecimal(Integer.MAX_VALUE);
            for (Element element : elements) {
                String text = element.text();
                text = text.replaceAll(",", "");
                if (new BigDecimal(text).compareTo(min) < 0) {
                    min = new BigDecimal(text);
                    finalmin = min;
                }
            }

            Elements box = doc.getElementsByClass("box");
            Elements select = box.select("span[class=price]");
            String shijia = select.get(0).text();
            shijia = shijia.replaceAll("CNY", "").replaceAll(" ", "").replaceAll(",", "");
            finalshijia = new BigDecimal(shijia);
            finalyijia = finalmin.subtract(finalshijia);

            String format = yymmdd.format(new Date());
            String join = Joiner.on(",").join(format, finalmin, finalshijia, finalyijia) + "\n";
            System.out.println(join);
            Files.append(join, file, Charsets.UTF_8);
            TimeUnit.SECONDS.sleep(1);
        }


    }

    static SimpleDateFormat yymmdd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


}
