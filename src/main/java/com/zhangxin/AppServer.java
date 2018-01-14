package com.zhangxin;

import com.zhangxin.biz.ConnectionHolder;
import com.zhangxin.utils.SleepUtils;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangxin on 2018/1/14.
 */
@Component
public class AppServer implements InitializingBean {
    private ExecutorService executors = Executors.newCachedThreadPool(Executors.defaultThreadFactory());

    @Override
    public void afterPropertiesSet() throws Exception {
        //=======eth========
//        executors.submit(() -> {
//
//        })

    }

    public Runnable dealETHPrice() {
        return () -> {
            while (!Thread.interrupted()) {
                try {
                    Document sellpage = ConnectionHolder.eth_sell_connection.get();
                    Document buypage = ConnectionHolder.eth_buy_connection.get();



                } catch (Exception e) {
                    e.printStackTrace();
                }
                SleepUtils.sleepRandomSec(10, 5);
            }
        };
    }
}
