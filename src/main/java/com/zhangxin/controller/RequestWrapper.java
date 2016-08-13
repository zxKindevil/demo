package com.zhangxin.controller;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhangxin
 *         Created on 16/8/5.
 */
public class RequestWrapper extends HttpServletRequestWrapper {
    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request
     * @throws IllegalArgumentException if the request is null
     */
    public RequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        System.out.println(name);
        if(name.equals("hosCode")){
            String value = super.getParameter(name);
            return this.mock(value);
        }
        return super.getParameter(name);
    }

    @Override
    public String[] getParameterValues(String name) {
        System.out.println(name);
        if(name.equals("hosCode")){
            String[] values = super.getParameterValues(name);
            return this.mock(values);
        }
        return super.getParameterValues(name);
    }

    private String mock(String hosCode) {
        Preconditions.checkNotNull(hosCode);
        if (hosCode.startsWith("T")) {
            hosCode = "H" + hosCode.substring(1, hosCode.length());
        }
        if (hosCode.startsWith("H")) {
            hosCode = "T" + hosCode.substring(1, hosCode.length());
        }
        return hosCode;
    }

    private String[] mock(String[] hosCodes){
        if(hosCodes == null){
            return null;
        }
        for(int i=0;i<hosCodes.length;i++){
            hosCodes[i] = this.mock(hosCodes[i]);
        }
        return hosCodes;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        System.out.println("xxxxxxx");
        String s = StreamUtils.copyToString(super.getInputStream(), Charsets.UTF_8);
        s = MockUtil.mockJson(s);
        System.out.println(s);

        InputStream byteArrayInputStream = new ByteArrayInputStream(s.getBytes());
        return new MockInputStream(byteArrayInputStream);
    }
}
