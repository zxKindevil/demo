package com.proxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhangxin
 *         Created on 18/7/25.
 */
public class JDKProxyTest {
    public static void main(String[] args) {
        HelloService hello = new HelloImpl();
        hello = (HelloService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{HelloService.class}, new HelloProxy(hello));
        hello.say();
    }

    public static void genClass() {
        String path = "/Users/zhangxin/z/ideaProject/dev/temp/demo/ProxyGTest.class";
        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", HelloImpl.class.getInterfaces());
        FileOutputStream out = null;

        try {
            out = new FileOutputStream(path);
            out.write(classFile);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
