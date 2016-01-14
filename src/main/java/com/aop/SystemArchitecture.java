package com.aop;

import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.stereotype.Service;

/**
 * ↓↓↓↓↓↓↓↓↓↓描述↓↓↓↓↓↓↓↓↓↓
 *
 * @author zhangxin.zhang created on 16-1-4.
 */
@Service
public class SystemArchitecture {
    @Idempotent
    public void businessService(){
        throw new PermissionDeniedDataAccessException("dd",new RuntimeException());
    }
}
