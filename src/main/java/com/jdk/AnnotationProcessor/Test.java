package com.jdk.AnnotationProcessor;

/**
 * ↓↓↓↓↓↓↓↓↓↓描述↓↓↓↓↓↓↓↓↓↓
 * @author zhangxin.zhang created on 16-1-26.
 */
public class Test {
    @PrintMe
    public void test(){
        System.out.println("done");
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.test();
    }
}
