package com.zhangxin.dao;

import com.zhangxin.bean.TempBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.jar.Attributes;

/**Created by zhangxin.zhang on 2015/4/13.
 */
@Component
public interface TempBeanDao {
    void insert(TempBean tempBean);
    List<TempBean> selectAll();

    TempBean selectById(@Param("id") int id,@Param("tempstr") String tempstr);
}
