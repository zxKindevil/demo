package com.guava.eventbus.example2;

import com.google.common.eventbus.Subscribe;

/**
 * ↓↓↓↓↓↓↓↓↓↓描述↓↓↓↓↓↓↓↓↓↓
 *
 * @author zhangxin.zhang created on 15-12-12.
 */
//Would only be notified of Cash purchase events
 class CashPurchaseSubscriber {
    @Subscribe
    public void handleCashPurchase(CashPurchaseEvent event){
    }
}
//Would only be notified of credit purchases
 class CreditPurchaseSubscriber {
    @Subscribe
    public void handleCreditPurchase(CreditPurchaseEvent event) {
    }
}
//Notified of any purchase event
public class PurchaseSubscriber {
    @Subscribe
    public void handlePurchaseEvent(PurchaseEvent event) {
    }
}
