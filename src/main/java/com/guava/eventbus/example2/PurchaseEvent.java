package com.guava.eventbus.example2;

/**
 * ↓↓↓↓↓↓↓↓↓↓描述↓↓↓↓↓↓↓↓↓↓
 *
 * @author zhangxin.zhang created on 15-12-12.
 */
public abstract class PurchaseEvent {
    String item;
    public PurchaseEvent(String item){
        this.item = item;
    }
}

