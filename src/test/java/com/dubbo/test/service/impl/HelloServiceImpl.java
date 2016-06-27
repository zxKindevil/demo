package com.dubbo.test.service.impl;

import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.dubbo.test.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @author zhangxin.zhang created on 16-6-23.
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String say() {
        Invocation invocation = RpcContext.getContext().getInvocation();
        System.out.println(RpcContext.getContext().getUrl().getParameter("application"));
        System.out.println("hello dubbo");
        throw new RuntimeException(new RuntimeException(new RpcException(RpcException.FORBIDDEN_EXCEPTION,"流量控制")));
    }
}
