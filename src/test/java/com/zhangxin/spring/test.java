package com.zhangxin.spring;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * @author zhangxin
 *         Created on 17/9/13.
 */
public class test {

    public static void main(String[] args) {
        ClassPathResource resource = new ClassPathResource("test-spring.xml");
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(resource);
        HelloService hello = (HelloService) factory.getBean("helloService");
        hello.hello();
    }


}
