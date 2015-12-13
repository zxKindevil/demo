package com.guava.eventbus;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * ↓↓↓↓↓↓↓↓↓↓描述↓↓↓↓↓↓↓↓↓↓
 *
 * @author zhangxin.zhang created on 15-12-12.
 */
public class PurchaseSubscriber {

    @Subscribe
    public void handlePurchaseEvent(PurchaseEvent event) {
        System.out.println(this.getClass().getSimpleName()+" accept");
    }

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        PurchaseSubscriber purchaseSubscriber = new PurchaseSubscriber();
        eventBus.register(purchaseSubscriber);

        eventBus.post(new PurchaseEvent());
    }
}


