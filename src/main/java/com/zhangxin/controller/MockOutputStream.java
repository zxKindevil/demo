package com.zhangxin.controller;

import javax.servlet.ServletOutputStream;
import java.io.*;

/**
 * @author zhangxin
 *         Created on 16/8/5.
 */
public class MockOutputStream extends ServletOutputStream {
    public OutputStream srcInputStream;
    public ByteArrayOutputStream mockStream;

    public MockOutputStream(OutputStream srcInputStream) {
        this.srcInputStream = srcInputStream;
        this.mockStream = new ByteArrayOutputStream();
    }

    public void write(int b) throws IOException {
        srcInputStream.write(b);
        mockStream.write(b);
    }
}
