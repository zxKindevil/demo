package com.zhangxin.controller;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.ResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

/**
 * @author zhangxin
 *         Created on 16/8/5.
 */
@Service
public class TestFilter extends OncePerRequestFilter {

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        RequestWrapper requestWrapper = new RequestWrapper(request);
        filterChain.doFilter(requestWrapper, response);
        return;
    }

    public Map<String, String> trans(HttpServletRequest request) {
        Enumeration parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = (String) parameterNames.nextElement();
            System.out.println(request.getParameter(name));
        }
        return Maps.newHashMap();
    }

    public static void main(String[] args) {
        String str="{\"inner\":{\"hosCode\":\"H32434324\"}}";
        int i = str.indexOf("\"hosCode\"");
        int start = str.indexOf("\"", i);
        int end = str.indexOf("\"", start);
    }
}
