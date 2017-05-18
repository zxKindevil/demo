package com.zhangxin.controller;

import com.alibaba.dubbo.rpc.*;

/**
 * @author zhangxin
 *         Created on 17/5/16.
 */
public class WhiteFilter implements Filter {
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        return invoker.invoke(invocation);
    }
}
