package com.zhangxin.processer;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * ↓↓↓↓↓↓↓↓↓↓描述↓↓↓↓↓↓↓↓↓↓
 *
 * @author zhangxin.zhang created on 15-11-27.
 */
@Service
public class ManualProcesser implements BeanPostProcessor{
    @Resource
    ApplicationContext applicationCtx;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        BeanDefinitionRegistry factory =
                (BeanDefinitionRegistry) applicationCtx.getAutowireCapableBeanFactory();
        boolean b = factory.containsBeanDefinition("org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping#0");
        if(b){
            BeanDefinition beanDefinition = factory.getBeanDefinition("org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping#0");
            beanDefinition.getPropertyValues().add("detectHandlerMethodsInAncestorContexts",true);

            MutablePropertyValues propertyValues = beanDefinition.getPropertyValues();
            System.out.println(propertyValues);
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
