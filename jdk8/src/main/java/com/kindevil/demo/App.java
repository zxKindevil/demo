package com.kindevil.demo;


import com.google.common.collect.Lists;
import sun.nio.ch.PollArrayWrapper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import static java.lang.System.out;

/**
 * Hello world!
 *
 */
public class App 
{


    public static void main( String[] args ) {
        ForkJoinPool pool = new ForkJoinPool();
        ArrayList<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        CountDownIterator<Integer> iterator = new CountDownIterator<>(100, list.size());
        pool.execute(()-> {
        });
        list.parallelStream().forEach(iterator::put);
        Iterator<Integer> it = iterator;
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }

    public static class CountDownIterator<T> implements Iterator<T> {
        private BlockingQueue<T> blockingQueue;
        private AtomicInteger count;

        CountDownIterator(int size,int count){
            this.blockingQueue = new ArrayBlockingQueue<>(size);
            this.count = new AtomicInteger(count);
        }

        public void put(T t){
            try {
                System.out.println("x");
                blockingQueue.put(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public boolean hasNext() {
            return count.get()>0 || !blockingQueue.isEmpty();
        }

        @Override
        public T next() {
            T ret = null;
            while( ret == null && this.hasNext()){
                try {
                    ret = blockingQueue.poll(1, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count.decrementAndGet();
            return ret;
        }
    }
}
