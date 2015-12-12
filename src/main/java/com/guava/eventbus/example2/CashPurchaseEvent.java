package com.guava.eventbus.example2;

/**
 * ↓↓↓↓↓↓↓↓↓↓描述↓↓↓↓↓↓↓↓↓↓
 *
 * @author zhangxin.zhang created on 15-12-12.
 */
public class CashPurchaseEvent extends PurchaseEvent {
    int amount;
    public CashPurchaseEvent(String item, int amount){
        super(item);
        this.amount = amount;
    }
}