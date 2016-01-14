package com.ch01_starup_shutdown_callback;

import org.springframework.context.LifecycleProcessor;
import org.springframework.stereotype.Service;

/**
 * ↓↓↓↓↓↓↓↓↓↓描述↓↓↓↓↓↓↓↓↓↓
 *
 * @author zhangxin.zhang created on 15-12-21.
 */
@Service
public class LifeCycleTest implements LifecycleProcessor {
    @Override
    public void onRefresh() {
        System.out.println("context refresh");
    }

    @Override
    public void onClose() {

    }

    @Override
    public void start() {
        System.out.println("bean 初始化");
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
