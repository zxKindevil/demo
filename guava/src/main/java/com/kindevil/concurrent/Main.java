package com.kindevil.concurrent;

/**
 * @author zhangxin.zhang created on 16-5-14.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        RateLimiter rateLimiter = RateLimiter.create(1);
        //尝试获取令牌
        rateLimiter.tryAcquire();
        rateLimiter.acquire();
    }
}
/**
 RateLimiter现在实现
 1.tryAcquire()
 所有线程竞争一个锁(看看是个什么锁)
 竞争到之后,并且可以获得令牌
 直接获取到令牌,并把下次可获得时间延迟

 修改后实现:
 1.tryAcquire()
 a.不能获得锁,返回sleep时间
 b.能获得锁,增加令牌,最终不能获得锁,退回令牌
 如果全部放在最后一起消耗令牌,会导致前令牌流量短暂虚高,
 如果失败补回令牌的话,会导致前令牌流量实际减低
 */