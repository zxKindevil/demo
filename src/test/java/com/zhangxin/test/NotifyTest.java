package com.zhangxin.test;

import com.zhangxin.biz.Configs;
import com.zhangxin.utils.Constant;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangxin on 2018/1/13.
 */
public class NotifyTest {
    public static String cookie = "akoxTldqY2ZEa3pRcTRFdVZPQkRBSmo3T0JHeXN3NXRGQTdvMnZGbmZtK01hSDZEdWN2SFFQTCtITDJzdUt0eiswRnNJeTAzVG9FZlMxZGg3Z0Uyb3FQWlV1Umx2Nkd3WHE4QWNjV2laWjBkNXpZUERMdy9PaHZwU21uQ3ExVWg3L1QwZlFETVk5WU4xbytva05DQThtMGpXWFEzTmxkNTBNTFpYcS9mT04yYXVDYUNFRThDaUZqc3hlaXJXMjB1R0UxTEZrY3pKbjN5N1dDN3BQODIyNTMrZnBJZWVHNHI1bGZyZ08wTVN6RTZtakFiZTl4cGVrL2YrYWc2Z2JabDREUk5Jazg4S3p1LzhmNmlLQnh3V0g5dGoyT0k0Q0ZrQTh2OUlMZmo4d05kbEdlOU9xWWE0YTlJcndKSGI0b2xzZE83M0tXUWdvL3dVb3hZbE5QQkJ2OWpXR2YyeXZNUk5ZYmJqaE5kUHg2Wk1XbFJZVG1MS3ZneTZFOGErdlRBOGtDUStKUnN0TGJVQVpkNldKK0U2bkVrWmpQV1BXNlNpeEpyWWdROGRyeHE1UGs3dkY5MW12TVBhVjlXdURrYmhpQ3VpcGdCMzVGYThPbkdMNFJHbEN3UVFDb0gveFB4QWhrbXh3dU54cTQ9LS1mQ2g1SXQ4QmdyeHI2Q01YLzdQUXd3PT0%3D--8ad90270b8b675a23cbc696393280c973389a8e1";

    public static void main(String[] args) throws IOException, ParseException, InterruptedException {
        while (!Thread.interrupted()) {
            try {
                Document doc = Jsoup.connect("https://otcbtc.com/account/notifications")
                        .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36")
                        .cookie("_otcbtc_session", cookie)
                        .get();

                Elements time = doc.getElementsByClass("time");
                Element element = time.get(1);
                String text = element.text() + ":00";
                Date parse = Constant.YYMMDD.parse(text);

                long min2 = TimeUnit.MINUTES.toMillis(2);
                if (new Date().getTime() - parse.getTime() < min2) {
                    play();
                }
            } catch (Exception e) {
                e.printStackTrace();
                play();
            }
            sleepRandom(60, 20);
        }
    }

    public static void sleepRandom(int max, int min) {
        try {
            int sleeptime = min + Math.abs(new Random().nextInt()) % (max - min);
            System.out.println("sleep sec " + sleeptime);
            TimeUnit.SECONDS.sleep(sleeptime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void play() {
        try {
            File file = new File("/Users/zhangxin/z/me/吉.wav"); // 获取文件，传入的参数为String类型，文件的路径
            URL url = file.toURL(); //获取文件的路径
            AudioClip ac = Applet.newAudioClip(url); // 因为AudioClip是接口不能实例化，所有用Applet中的newAduioClip来实例化
            ac.play(); //播放
        } catch (MalformedURLException e) {
            System.out.println(e);
        }
    }
}
