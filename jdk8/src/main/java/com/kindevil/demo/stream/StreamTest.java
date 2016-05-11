package com.kindevil.demo.stream;

import com.google.common.collect.Lists;

import java.util.stream.Collectors;

/**
 * @author zhangxin.zhang created on 16-5-10.
 */
public class StreamTest {
    public static void main(String[] args) {
        Lists.newArrayList(1,2,3,4,5,6,7,8,9)
                .stream()
                .collect(Collectors.toList());
    }
}
