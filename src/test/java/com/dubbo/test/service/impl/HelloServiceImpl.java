package com.dubbo.test.service.impl;

import com.dubbo.test.service.HelloService;

/**
 * @author zhangxin.zhang created on 16-6-23.
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String say() {
        System.out.println("hello dubbo");
        return "heelo";
    }
}
