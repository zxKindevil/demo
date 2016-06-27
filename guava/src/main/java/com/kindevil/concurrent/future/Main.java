package com.kindevil.concurrent.future;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * @author zhangxin.zhang created on 16-6-27.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println();

        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture explosion = service.submit(new Callable() {
            public String call() {
                return "success";
            }
        });

        Futures.addCallback(explosion, new FutureCallback() {

            @Override
            public void onSuccess(Object result) {
                System.out.println(result);
            }

            public void onFailure(Throwable thrown) {
                System.out.println(thrown);
            }
        });
    }
}
