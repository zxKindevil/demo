package com.dubbo.test;

import com.alibaba.dubbo.remoting.exchange.ResponseCallback;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcResult;
import com.alibaba.dubbo.rpc.protocol.dubbo.FutureAdapter;
import com.dubbo.test.service.HelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author zhangxin.zhang created on 16-6-23.
 */
public class ConsumerMain {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:spring-consumer-root.xml"});
        context.start();

        final HelloService helloService = (HelloService) context.getBean("helloService");
//        helloService.say();
//
//        FutureAdapter future = (FutureAdapter) RpcContext.getContext().getFuture();
//        future.getFuture().setCallback(new ResponseCallback() {
//            public void done(Object o) {
//                RpcResult rpcResult = ((RpcResult) o);
//                if (rpcResult.hasException()) {
//                    //log
//                } else {
//                    System.out.println(rpcResult.getValue());
//                }
//            }
//
//            public void caught(Throwable throwable) {
//                //log
//            }
//        });

        Future<String> future = RpcContext.getContext().asyncCall(new Callable<String>() {
            public String call() throws Exception {
                return helloService.say();
            }
        });

        System.out.println(future.get());

    }
}
