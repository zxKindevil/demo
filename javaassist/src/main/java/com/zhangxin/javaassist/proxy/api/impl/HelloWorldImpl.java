package com.zhangxin.javaassist.proxy.api.impl;

import com.zhangxin.javaassist.proxy.api.HelloWorld;

/**
 * @author zhangxin.zhang created on 16-4-10.
 */
public class HelloWorldImpl implements HelloWorld {
    @Override
    public void say() {
        System.out.println("hello world");
    }
}
