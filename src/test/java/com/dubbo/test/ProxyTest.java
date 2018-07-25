package com.dubbo.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhangxin
 *         Created on 18/7/25.
 */
public class ProxyTest {
    public static void main(String[] args) {
        HelloService hello = new HelloImpl();
        HelloService proxy = (HelloService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{HelloService.class}, new HelloProxy(hello));
        proxy.say();
    }
}

interface HelloService {
    void say();
}

class HelloImpl implements HelloService {

    @Override
    public void say() {
        System.out.println("执行业务方法");
    }
}

class HelloProxy implements InvocationHandler {
    private HelloService helloService;

    public HelloProxy(HelloService business) {
        this.helloService = business;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before invoke");
        Object result = method.invoke(helloService, args);
        System.out.println("after invoke");
        return result;
    }
}