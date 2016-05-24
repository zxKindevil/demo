package com.vertx.http;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;

/**
 * @author zhangxin.zhang created on 16-5-23.
 */
public class Server {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.createHttpServer().requestHandler(request -> {
            request.response().end("Hello world fuck");
        }).listen(8080);
    }
}
