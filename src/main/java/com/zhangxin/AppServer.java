package com.zhangxin;

import com.zhangxin.biz.Configs;
import com.zhangxin.biz.ConnectionHolder;
import com.zhangxin.biz.ETHPriceHandler;
import com.zhangxin.biz.OrderNotifyHandler;
import com.zhangxin.restapi.HttpWrapper;
import com.zhangxin.restapi.inf.PriceNotifyer;
import com.zhangxin.utils.Constant;
import com.zhangxin.utils.SleepUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangxin on 2018/1/14.
 */
@Component
public class AppServer implements InitializingBean {
    private ExecutorService executors = Executors.newFixedThreadPool(4);

    @Resource
    private ETHPriceHandler handler;
    @Resource
    private OrderNotifyHandler orderNotifyHandler;
    @Resource
    private HttpWrapper httpWrapper;
    @Resource
    private PriceNotifyer priceNotifyer;

    @Override
    public void afterPropertiesSet() throws Exception {


        System.out.println("============= spring boot init ===========");
        //同步加载配置文件
        Configs.initListenConfig();
        //抓取报价

        while (true) {
            System.out.println(Configs.getString("total.life.count"));
            TimeUnit.SECONDS.sleep(1);
        }

//        executors.submit(dealRestBi());

//        executors.submit(dealETHPrice());
//
//        executors.submit(dealOrderNotify());
    }

    public Runnable dealRestBi() {
        return () -> {
            while (!Thread.interrupted()) {
                try {
                    priceNotifyer.deal();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                SleepUtils.sleepRandomSec(3, 1);
            }
        };
    }

    public Runnable dealOrderNotify() {
        return () -> {
            while (!Thread.interrupted()) {
                if (Configs.getBoolean("switch.open.order.notify")) {
                    try {
                        Document orderNotifyPage = Jsoup.connect(Constant.ORDER_NOTIFYS)
                                .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36")
                                .cookie("_otcbtc_session", Configs.getString("user.lg.cookie"))
                                .get();

                        orderNotifyHandler.handle(orderNotifyPage);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                SleepUtils.sleepRandomSec(10, 5);
            }
        };
    }

    public Runnable dealETHPrice() {
        return () -> {
            while (!Thread.interrupted()) {
                try {
                    Document sellpage = ConnectionHolder.eth_sell_connection.get();
                    Document buypage = ConnectionHolder.eth_buy_connection.get();

                    handler.handle(sellpage, buypage);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                SleepUtils.sleepRandomSec(10, 5);
            }
        };
    }


}
