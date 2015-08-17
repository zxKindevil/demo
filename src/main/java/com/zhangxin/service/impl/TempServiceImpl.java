package com.zhangxin.service.impl;

import com.zhangxin.bean.TempBean;
import com.zhangxin.dao.TempBeanDao;
import com.zhangxin.service.TempService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**Created by zhangxin.zhang on 2015/4/13.
 */
@Service
public class TempServiceImpl implements TempService {
    private final static Logger logger = LoggerFactory.getLogger(TempServiceImpl.class);

    @Resource
    TempBeanDao tempBeanDao;

    @Override
    public void insert(TempBean tempBean) {
        tempBeanDao.insert(tempBean);
        logger.info("{}",tempBeanDao.selectAll());
    }


    @Override
    @Transactional
    public void testTransation(boolean flag)  {
        logger.info("test begin:{}",tempBeanDao.selectAll().size());
        tempBeanDao.insert(new TempBean("aaaaa"));
        tempBeanDao.insert(new TempBean("vvvv"));
        tempBeanDao.insert(new TempBean("bbbb"));
        tempBeanDao.insert(new TempBean("aaaa"));
        logger.info("testing:{}",tempBeanDao.selectAll().size());
        if(flag){
            throw new RuntimeException("test transation");
        }
        tempBeanDao.insert(new TempBean("ccccc"));
        tempBeanDao.insert(new TempBean("aaaaa"));
    }
}
