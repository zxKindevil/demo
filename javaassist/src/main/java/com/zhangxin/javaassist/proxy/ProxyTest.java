package com.zhangxin.javaassist.proxy;

import com.zhangxin.javaassist.proxy.api.HelloWorld;
import com.zhangxin.javaassist.proxy.api.impl.HelloWorldImpl;
import com.zhangxin.javaassist.proxy.handler.HelloWorldHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author zhangxin.zhang created on 16-4-9.
 */
public class ProxyTest {
    public static void main(String[] args) {
        HelloWorld helloWorld=new HelloWorldImpl();
        InvocationHandler handler=new HelloWorldHandler(helloWorld);

        //创建动态代理对象
        HelloWorld proxy=(HelloWorld) Proxy.newProxyInstance(
                helloWorld.getClass().getClassLoader(),
                helloWorld.getClass().getInterfaces(),
                handler);
        proxy.say();
    }
}
