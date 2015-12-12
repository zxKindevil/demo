package com.guava.eventbus.example2;

/**
 * ↓↓↓↓↓↓↓↓↓↓描述↓↓↓↓↓↓↓↓↓↓
 *
 * @author zhangxin.zhang created on 15-12-12.
 */
public class CreditPurchaseEvent extends PurchaseEvent {
    int amount;
    String cardNumber;
    public CreditPurchaseEvent(String item,int amount, String cardNumber){
        super(item);
        this.amount = amount;
        this.cardNumber = cardNumber;
    }
}