/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dubbo.test.benmuasync;

import com.alibaba.dubbo.rpc.RpcContext;
import com.dubbo.test.benmuasync.api.AsyncService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * CallbackConsumer
 */
public class AsyncConsumer {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo/benmu/async/async-consumer.xml");
        context.start();

        final AsyncService asyncService = (AsyncService) context.getBean("asyncService");

        Future<String> f = RpcContext.getContext().asyncCall(new Callable<String>() {
            public String call() throws Exception {
                return asyncService.sayHello("async call request");
            }
        });

        System.out.println("async call ret :" + f.get());

        TimeUnit.SECONDS.sleep(2);

    }

}
