package com.zhangxin.controller;

import com.zhangxin.bean.TempBean;
import com.zhangxin.service.TempService;
import com.zhangxin.exception.TempException;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/temp")
public class CtrlTemp {

    @Resource
    TempService tempService;

	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public String helloGet(Model model){
		System.out.println("helloGet");
		return "temp";
	}
	
	@RequestMapping(value="/hello",method=RequestMethod.POST)
	public String helloPost(String name,HttpSession session,Model model){

        if( name.equals("error") ){
			throw new TempException("test error!");
		}
		session.setAttribute("name", name);
        model.addAttribute("welcome","SpringMVC欢迎你:配置教程" +
                "http://wiki.corp.qunar.com/pages/viewpage.action?pageId=73283087");
		return "temp";
	}

    @RequestMapping(value = "/beanvalidation")
    public String beanvalidation(@Valid TempBean tempBean, BindingResult result) throws Exception{
        if(result.hasErrors()){
            return "error";
        }
        tempService.insert(tempBean);
        return "temp";
    }
}
