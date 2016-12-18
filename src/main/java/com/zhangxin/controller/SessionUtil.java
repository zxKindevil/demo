package com.zhangxin.controller;

import com.google.common.collect.Iterables;

import java.util.List;
import java.util.Map;

/**
 * @author zhangxin
 *         Created on 16/12/16.
 */
public class SessionUtil {
    public static String getParam(Map<String, List<String>> params, String key) {
        return Iterables.getFirst(params.get(key), null);
    }
}
