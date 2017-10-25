package com.zhangxin.spring;

/**
 * @author zhangxin
 *         Created on 17/9/14.
 */
public class HelloServiceImpl implements HelloService {
    private String str = "hello";

    public void hello() {
        System.out.println(str);
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
