package com.zhangxin.spring;


/**
 * @author zhangxin
 *         Created on 17/9/13.
 */
public class test {
    public static void main(String[] args) {
        Col<Integer> col = new Col<Integer>(1, new Col<Integer>(2, new Nil<Integer>()));
    }
}

class List<T> {

}

class Nil<T> extends List<T> {

}

class Col<T> extends List<T> {
    T head;
    List<T> tl;

    public Col(T head, List<T> tl) {
        this.head = head;
        this.tl = tl;
    }
}
