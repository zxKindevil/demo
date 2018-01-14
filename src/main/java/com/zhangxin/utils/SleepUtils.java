package com.zhangxin.utils;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangxin on 2018/1/14.
 */
public class SleepUtils {
    public static void sleepRandomSec(int max, int min) {
        try {
            int sleeptime = min + Math.abs(new Random().nextInt()) % (max - min);
            System.out.println("sleep sec " + sleeptime);
            TimeUnit.SECONDS.sleep(sleeptime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
