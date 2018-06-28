package com.dubbo.test.service;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.benmu.mts.wx.center.controller.TestService;

/**
 * @author zhangxin
 *         Created on 18/6/28.
 */
public class TestMain {
    public static void main(String[] args) {
        ApplicationConfig application = new ApplicationConfig("test");

        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("10.32.64.12:2181,10.32.64.19:2181,10.32.64.161:2181");
        registry.setGroup("mts-wx-center-test");
        registry.setProtocol("zookeeper");

        ReferenceConfig<TestService> reference = new ReferenceConfig<TestService>(); // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
        reference.setApplication(application);
        reference.setRegistry(registry); // 多个注册中心可以用setRegistries()
        reference.setInterface(TestService.class);
        reference.setVersion("1.0");

        TestService testService = reference.get();
        System.out.println(testService.deal("test dubbo"));
    }
}
