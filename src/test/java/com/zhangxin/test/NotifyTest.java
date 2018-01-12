package com.zhangxin.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangxin on 2018/1/13.
 */
public class NotifyTest {

    public static SimpleDateFormat yymmdd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws IOException, ParseException, InterruptedException {
        Document doc = Jsoup.connect("https://otcbtc.com/account/notifications")
                .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36")
                .cookie("_otcbtc_session","eTBhZUZLMkpJOVFpTGlSYzZoYWI5dzlTUUJtSU80c0x4Yjc3MjBBYURFM1pBSlJ1UDVlSkdOcWZXRXJCVFRVZDV6NVJQVGZGNEpqeVNyVkNibUxEc1dQWkxoZnV0MlJRVjBFeTVUMmgyWW1ITW5taDRJK2pocE9Id0tsT3lUOHBVV2tHVkp2UUdaemQzT1RBbzRHQStyTDZwc29xOEUzRkt3eWtuMWF5MHRKY3lwRG03S3JZNUI5N3VId0VBOTVrWUp2MWlqcjAzKzV5Z0NFYkVBY3hGRzNDdXp3dThtd3VaYUdsMDBKMWxPcWJkdFQyaGhiMWxTV0xjTU9mak00emxLTWZidm1ON2ZmZnJNN0FRTU55aDREUlJidGZQeUF5MGlTQ3cxRnNhalpQaXEzS2lVNE1wREk2QW1ZYU9ZV2UxYjJ5QmJ4SGhxYk13ZXRGVVFpZE5adk9hWGo3M1F2WmUvV1FCSm1MT3VhWjlzeEpLSmxkdzlDbUs3ZFJCVDRna3dyb05XUmNmWDFPZjVlM1pYMWxyNjBYOE5JRTY5ejBXOC9oQ2MwdXBacW9TMmhmK3Iwa1M2dGxtN1hPc29SRDRzTWduMi9lOURkc1g1TUJQVGNoS0Z0UlJkV2t4YkllN2lPWXVoeEcxYUE9LS1aRHdJQWpGU1dpWktvT0NGUzlSQkFBPT0%3D--ec0e6a4c2dfb27f171c80604bd071496c180b183")
                .get();

        Elements time = doc.getElementsByClass("time");
        Element element = time.get(1);
        String text = element.text()+":00";
        Date parse = yymmdd.parse(text);

        long min2 = TimeUnit.MINUTES.toMillis(120);
        if (new Date().getTime() - parse.getTime() < min2) {
            for(int i=0;i<2;i++){
                Toolkit.getDefaultToolkit().beep();
                TimeUnit.SECONDS.sleep(1);
            }
        }

        System.out.println(parse);


        System.out.println(time);

    }
}
