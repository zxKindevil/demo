package com.zhangxin.test.spring;

import com.zhangxin.ApplicationBoot;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhangxin on 2018/1/15.
 */
@RunWith(SpringRunner.class)
@org.springframework.boot.test.context.SpringBootTest(classes = ApplicationBoot.class, webEnvironment = org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class SpringMyBootTest {


    @Test
    public void getHello() throws Exception {

    }
}