package com.zhangxin.controller;

import com.google.common.collect.Lists;
import com.zhangxin.bean.TempBean;
import com.zhangxin.service.TempService;
import com.zhangxin.exception.TempException;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import manualcontroller.ManualController;
import manualcontroller.xml.ComponentFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;

@Controller
@RequestMapping("/temp")
public class CtrlTemp {

    @Resource
    ComponentFactoryBean componentFactoryBean;
    @Resource
    ManualController manualController;
    @Resource
    TempService tempService;

    @RequestMapping(value="/actor",method=RequestMethod.GET)
    public ModelAndView actor(ModelAndView model){
        System.out.println("actor");
        List<Integer> list = Lists.newArrayList(1,2,3,4,5);
//        InternalResourceView view = new InternalResourceView();
        InternalResourceView view = new JstlView();
        view.setUrl("/actor.jsp");
        model.setView(view);
        model.addObject("list", list);
        return model;
    }

	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public String helloGet(Model model){
		System.out.println("helloGet");
        System.out.println(componentFactoryBean.getObject());
        System.out.println(manualController);

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
