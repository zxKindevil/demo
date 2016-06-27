package com.kindevil.concurrent.future;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainTest {

    /**
     * MoreExecutors.listeningDecorator
     */
    @Test
    public void test(){
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

    /**
     * future
     */
    @Test
    public void testa(){
        System.out.println();

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<String> callable = executorService.submit(new Callable() {
            public String call() {
                return "success";
            }
        });
        ListenableFutureTask.create(new Callable() {
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