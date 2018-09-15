package com.zhangxin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhangxin
 *         Created on 18/3/21.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/param")
    @ResponseBody
    String home(String str, Integer num) {
        System.out.println(str + num);
        return "Hello World!" + str + num;
    }
}
