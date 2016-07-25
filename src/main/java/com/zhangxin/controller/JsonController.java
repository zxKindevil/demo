package com.zhangxin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhangxin
 *         Created on 16/7/26.
 */
@Controller
@RequestMapping("/json")
public class JsonController {

    @RequestMapping("/test")
    @ResponseBody
    public Object helloGet(@RequestBody A a){
        System.out.println(a.getStr());
        System.out.println(a.getTest());
        return new A();
    }
}

class A {
    private String str = "a";
    private String test = "b";

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
