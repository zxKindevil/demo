package com.dubbo.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author zhangxin.zhang created on 16-6-23.
 */
public class ProviderMain {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:spring-provider-root.xml"});
        context.start();
        System.out.println("start");
        System.in.read();
    }
}
