package com.zhangxin.controller;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
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
public class HelloController {
    public static SimpleDateFormat yymmdd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String BUY = "https://otcbtc.com/sell_offers?currency=eth&fiat_currency=cny&payment_type=all";
    public static String SALE = "https://otcbtc.com/buy_offers?currency=eth&fiat_currency=cny&payment_type=all";
    public static String notify = "https://otcbtc.com/account/notifications";

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/test")
    public String index(HttpServletRequest request, @RequestParam String str, @RequestParam int num) {
        return "Greetings from Spring Boot!";
    }

    public void deal() throws IOException, InterruptedException {
        Connection connect = Jsoup.connect(BUY);

        Connection saleSession = Jsoup.connect(SALE);

        Connection notify_session = Jsoup.connect(notify)
                .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36")
                .cookie("_otcbtc_session", "eTBhZUZLMkpJOVFpTGlSYzZoYWI5dzlTUUJtSU80c0x4Yjc3MjBBYURFM1pBSlJ1UDVlSkdOcWZXRXJCVFRVZDV6NVJQVGZGNEpqeVNyVkNibUxEc1dQWkxoZnV0MlJRVjBFeTVUMmgyWW1ITW5taDRJK2pocE9Id0tsT3lUOHBVV2tHVkp2UUdaemQzT1RBbzRHQStyTDZwc29xOEUzRkt3eWtuMWF5MHRKY3lwRG03S3JZNUI5N3VId0VBOTVrWUp2MWlqcjAzKzV5Z0NFYkVBY3hGRzNDdXp3dThtd3VaYUdsMDBKMWxPcWJkdFQyaGhiMWxTV0xjTU9mak00emxLTWZidm1ON2ZmZnJNN0FRTU55aDREUlJidGZQeUF5MGlTQ3cxRnNhalpQaXEzS2lVNE1wREk2QW1ZYU9ZV2UxYjJ5QmJ4SGhxYk13ZXRGVVFpZE5adk9hWGo3M1F2WmUvV1FCSm1MT3VhWjlzeEpLSmxkdzlDbUs3ZFJCVDRna3dyb05XUmNmWDFPZjVlM1pYMWxyNjBYOE5JRTY5ejBXOC9oQ2MwdXBacW9TMmhmK3Iwa1M2dGxtN1hPc29SRDRzTWduMi9lOURkc1g1TUJQVGNoS0Z0UlJkV2t4YkllN2lPWXVoeEcxYUE9LS1aRHdJQWpGU1dpWktvT0NGUzlSQkFBPT0%3D--ec0e6a4c2dfb27f171c80604bd071496c180b183");

        File file = new File("price.txt");

        while (!Thread.interrupted()) {
            try {
                BigDecimal buymin;
                BigDecimal finalshijia;
                BigDecimal salemax;
                BigDecimal yijia;
                BigDecimal chajia;

                Pair<BigDecimal, BigDecimal> buyPair = this.parseBuyHtml(connect);
                salemax = this.parseSaleHtml(saleSession);
                buymin = buyPair.getLeft();
                finalshijia =  buyPair
                        .getRight();

                yijia = buymin.subtract(finalshijia);
                chajia = buymin.subtract(salemax);


                this.notifyme(notify_session);
                TimeUnit.SECONDS.sleep(20);
            } catch (Exception e) {
                //ignore
            }
        }
    }

    private BigDecimal parseSaleHtml(Connection saleSession) throws IOException {
        Document doc = saleSession.get();
        Elements elements = doc.getElementsByClass("recommend-card__price");
        BigDecimal max = BigDecimal.ZERO;
        for (Element element : elements) {
            String text = element.text();
            text = text.replaceAll(",", "");
            if (new BigDecimal(text).compareTo(max) > 0) {
                max = new BigDecimal(text);
            }
        }

        return max;
    }

    public Pair parseBuyHtml(Connection connect) throws IOException {
        Document doc = connect.get();
        Elements elements = doc.getElementsByClass("recommend-card__price");
        BigDecimal min = new BigDecimal(Integer.MAX_VALUE);
        for (Element element : elements) {
            String text = element.text();
            text = text.replaceAll(",", "");
            if (new BigDecimal(text).compareTo(min) < 0) {
                min = new BigDecimal(text);
            }
        }

        Elements box = doc.getElementsByClass("box");
        Elements select = box.select("span[class=price]");
        String shijia = select.get(0).text();
        shijia = shijia.replaceAll("CNY", "").replaceAll(" ", "").replaceAll(",", "");


        return Pair.of(min, new BigDecimal(shijia));

    }

    public void notifyme(Connection connection) {
        try {
            Elements time = connection.get().getElementsByClass("time");
            Element element = time.get(1);
            String text = element.text() + ":00";
            Date parse = yymmdd.parse(text);

            long min2 = TimeUnit.MINUTES.toMillis(2);
            if (new Date().getTime() - parse.getTime() < min2) {
                new Thread(() -> {
                    for (int i = 0; i < 15; i++) {
                        Toolkit.getDefaultToolkit().beep();
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}

class Pair<L, R> {
    L left;
    R right;

    public Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public static <L, R> Pair of(L left, R right) {
        return new Pair<>(left, right);
    }

    public L getLeft() {
        return left;
    }

    public void setLeft(L left) {
        this.left = left;
    }

    public R getRight() {
        return right;
    }

    public void setRight(R right) {
        this.right = right;
    }
}