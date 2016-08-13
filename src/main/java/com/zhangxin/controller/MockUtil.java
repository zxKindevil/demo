package com.zhangxin.controller;

/**
 * @author zhangxin
 *         Created on 16/8/5.
 */
public class MockUtil {
    public static String KEY = "\"hosCode\"";

    public static String mockJson(String json){
        String str= json;

        int cur=0;
        while(-1!=(cur = str.indexOf(KEY,cur+1))){
            str = rep(str,cur);
        }

        return str;
    }

    private static String rep(String str,int cur){
        int len = KEY.length();

        int i1 = str.indexOf("\"", cur+len);
        int end = str.indexOf("\"", i1+1);
        return str.replace(str.substring(cur,end+1), mock(str.substring(cur,end+1)));
    }
    private static String mock(String str){
        String[] split = str.split(":");
        String replace = split[1].replace("H", "T");
        return split[0]+":"+replace;
    }
}
