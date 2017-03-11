package com.zhangxin.test;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author zhangxin on 2017/3/10.
 */
public class TempTest {
    @Test
    public void test() {
        Lists.newArrayList().stream()
                .collect(Collectors.toList());
    }
}
