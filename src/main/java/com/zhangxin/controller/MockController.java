package com.zhangxin.controller;

import com.zhangxin.bean.Hospital;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhangxin
 *         Created on 16/8/5.
 */
@Controller
@RequestMapping("/hoscode")
public class MockController {

    @RequestMapping(value = "/mock")
    public String helloGet(String hosCode) {
        System.out.println("hosCode=" + hosCode);
        return "temp";
    }

    @RequestMapping(value = "/mockobj")
    @ResponseBody
    public Hospital sdsf(@RequestBody Hospital hospital) {
        System.out.println(hospital);
        return hospital;
    }


}

