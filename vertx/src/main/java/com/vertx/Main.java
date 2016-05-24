package com.vertx;

import com.vertx.core.MyVerticle;
import io.vertx.core.Vertx;

/**
 * @author zhangxin.zhang created on 16-5-22.
 */
public class Main {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new MyVerticle());
        vertx.setPeriodic(1000, id -> {
            // This handler will get called every second
            System.out.println("timer fired!");
        });
    }
}
