package com.zhangxin.test;

import com.zhangxin.bean.Article;
import com.zhangxin.bean.TempBean;
import com.zhangxin.bean.User;
import com.zhangxin.dao.TempBeanDao;
import com.zhangxin.dao.UserDao;
import com.zhangxin.service.TempService;
import com.zhangxin.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class MybatisSprintTest {
    private final static Logger logger = LoggerFactory.getLogger(MybatisSprintTest.class);

    @Resource
    UserDao userDao;
    @Resource
    TempBeanDao tempBeanDao;
    @Resource
    UserService userService;
    @Resource
    TempService tempService;

    @Test
    public void testA(){
        tempService.insert(new TempBean("test"));
        List<TempBean> tempBeans = tempBeanDao.selectAll();
        System.out.println(tempBeans);
    }

    @Test
    public void testB(){
        try {
            tempService.testTransation(true);
        }catch (Exception e){
            logger.error("",e);
        }
        System.out.println(tempBeanDao.selectAll().size());
    }

    @Test
    public  void test()
    {
        //test transation
        try {
            userService.test(true);
        }catch (Exception e){
            System.out.println("error");
        }

        System.out.println(userDao.selectLikeName("%"));
        //测试id=1的用户查询，根据数据库中的情况，可以改成你自己的.
        System.out.println("得到用户id=1的用户信息");
        User user = userDao.selectByID(1);
        System.out.println(user.getUserAddress());

        //得到文章列表测试
        System.out.println("得到用户id为1的所有文章列表");
        List<Article> articles = userDao.selectArticlesByUserID(1);

        for(Article article:articles){
            System.out.println(article.getContent()+"--"+article.getTitle());
        }
    }
}