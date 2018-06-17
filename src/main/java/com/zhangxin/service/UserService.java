package com.zhangxin.service;

import com.zhangxin.bean.User;
import com.zhangxin.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserService {
    private final static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Resource
    UserDao userDao;

    @Transactional
    public void test(boolean flag){
        userDao.insert(new User().setUserName("test01").setUserAge("1").setUserAddress("test"));
        userDao.insert(new User().setUserName("test02").setUserAge("1").setUserAddress("test"));
        if(flag)
            throw new RuntimeException("simple test");
        userDao.insert(new User().setUserName("test03").setUserAge("1").setUserAddress("test"));
    }
}
