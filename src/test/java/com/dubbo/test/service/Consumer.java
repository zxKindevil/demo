package com.dubbo.test.service;

import com.alibaba.dubbo.remoting.exchange.ResponseCallback;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.RpcResult;
import com.alibaba.dubbo.rpc.protocol.dubbo.FutureAdapter;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangxin.zhang created on 16-6-23.
 */
public class Consumer {
    public static void main(String[] args) throws IOException, InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:consumer/spring-root.xml"});
        context.start();
        HelloService bean = (HelloService)context.getBean("helloService", HelloService.class);
        bean.say();

        FutureAdapter<String> future = (FutureAdapter) RpcContext.getContext().getFuture();

        future.getFuture().setCallback(new ResponseCallback() {
            @Override
            public void done(Object response) {
                Throwable exception = ((RpcResult) response).getException();
                System.out.println(exception.getCause() instanceof RpcException);
                System.out.println("done");
                System.out.println(response);
            }

            @Override
            public void caught(Throwable exception) {
                System.out.println("err");
                System.out.println(exception);
            }
        });
        TimeUnit.SECONDS.sleep(5);
    }
}
