package com.concurrent.ch1_blockingQueue;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * ↓↓↓↓↓↓↓↓↓↓描述↓↓↓↓↓↓↓↓↓↓
 *
 * @author zhangxin.zhang created on 16-1-16.
 */
public class Main {

    public static final BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(3);

    @Test
    public void init(){
        try {
            blockingQueue.put(1);
            blockingQueue.put(2);
            blockingQueue.put(3);

            Thread A = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(blockingQueue.size());
                        blockingQueue.put(4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            A.start();

            Thread B = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(blockingQueue.size());
                        blockingQueue.put(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            B.start();


            A.join();
            B.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
