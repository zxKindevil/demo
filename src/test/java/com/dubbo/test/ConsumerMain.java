package com.dubbo.test;

import com.dubbo.test.service.HelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author zhangxin.zhang created on 16-6-23.
 */
public class ConsumerMain {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:spring-consumer-root.xml"});
        context.start();

        System.out.println("start");

        HelloService helloService = (HelloService) context.getBean("helloService");
        System.out.println(helloService.say());

    }
}
