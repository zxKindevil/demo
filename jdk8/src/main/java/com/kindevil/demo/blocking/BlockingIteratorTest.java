package com.kindevil.demo.blocking;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author zhangxin.zhang created on 16-5-10.
 */
public class BlockingIteratorTest {
    public static final BlockingQueue<Long> queue =  new ArrayBlockingQueue<>(10000);

    public static void main(String[] args) throws InterruptedException {
        queue.take();
    }
}
