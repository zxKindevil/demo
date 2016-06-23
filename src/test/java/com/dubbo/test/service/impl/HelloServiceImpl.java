package com.dubbo.test.service.impl;

import com.dubbo.test.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @author zhangxin.zhang created on 16-6-23.
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public void say() {
        System.out.println("hello dubbo");
    }
}
