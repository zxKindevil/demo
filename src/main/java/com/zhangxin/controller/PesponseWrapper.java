package com.zhangxin.controller;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * @author zhangxin
 *         Created on 16/8/5.
 */
public class PesponseWrapper extends HttpServletResponseWrapper {
    public MockOutputStream mockOutputStream;
    /**
     * Constructs a response adaptor wrapping the given response.
     *
     * @param response
     * @throws IllegalArgumentException if the response is null
     */
    public PesponseWrapper(HttpServletResponse response) throws IOException {
        super(response);
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return mockOutputStream;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        System.out.println("go here");
        return new PrintWriter(new OutputStreamWriter(super.getOutputStream(), getCharacterEncoding()));
    }
}
