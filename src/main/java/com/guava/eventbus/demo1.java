package com.guava.eventbus;

/**
 * ↓↓↓↓↓↓↓↓↓↓描述↓↓↓↓↓↓↓↓↓↓
 *
 * @author zhangxin.zhang created on 15-12-12.
 */
class Demo_1
{
    int  num;
    char ch;
}

public class demo1
{
    public static Object ch = null;
    public static void main(String[] args)
    {

        System.out.println(ch);
        Demo_1 demo = new Demo_1();
        System.out.println(demo.num); //默认0
        System.out.println((int)'!');  //默认'\0'
        System.out.println('\41');
    }

}
