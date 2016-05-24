package com.vertx.core;

import io.vertx.core.AbstractVerticle;

/**
 * @author zhangxin.zhang created on 16-5-23.
 */
public class MyVerticle extends AbstractVerticle {

    // Called when verticle is deployed
    public void start() {
        System.out.println("deployed");
    }

    // Optional - called when verticle is undeployed
    public void stop() {
    }

}