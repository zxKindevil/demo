package com.zhangxin.biz;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangxin on 2018/1/15.
 */
public class Configs {
    public static String configPath = Configs.class.getResource("/").getPath() + "config.properties";

    private static volatile Properties properties = null;

    private static ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);

    public static String getString(String key) {
        return String.valueOf(properties.get(key));
    }

    public static boolean getBoolean(String key) {
        return true;
    }

    public static int getInt(String key) {
        return Integer.valueOf((String) properties.get(key));
    }

    public static void initListenConfig() {
        try {
            scheduledThreadPool.scheduleAtFixedRate(
                    Configs::refresh,
                    2,
                    2,
                    TimeUnit.SECONDS);

            refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void refresh() {
        try {
            Properties temp = new Properties();
            temp.load(new FileInputStream(new File(configPath)));
            properties = temp;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
