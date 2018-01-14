package com.zhangxin.biz;

import com.zhangxin.utils.Constant;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangxin on 2018/1/15.
 */
@Service
public class OrderNotifyHandler {
    public void handle(Document page) {
        try {
            Elements time = page.getElementsByClass("time");
            Element element = time.get(1);
            String text = element.text() + ":00";
            Date parse = Constant.YYMMDD.parse(text);

            long min2 = TimeUnit.MINUTES.toMillis(Configs.getInt("neworder.notiy.time.min"));
            if (new Date().getTime() - parse.getTime() < min2) {
                SoundPlayer.playWolf();
            }
        } catch (ParseException e) {
            SoundPlayer.playWolf();
            e.printStackTrace();
        }
    }
}
