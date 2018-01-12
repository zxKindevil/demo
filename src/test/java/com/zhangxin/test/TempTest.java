package com.zhangxin.test;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.awt.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author zhangxin on 2017/3/10.
 */
public class TempTest {
    @Test
    public void test() throws InterruptedException {
        for(int i=0;i<100;i++){
            Toolkit.getDefaultToolkit().beep();
            TimeUnit.SECONDS.sleep(1);
        }

        Lists.newArrayList().stream()
                .collect(Collectors.toList());
    }
}
