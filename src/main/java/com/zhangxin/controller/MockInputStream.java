package com.zhangxin.controller;

import javax.servlet.ServletInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhangxin
 *         Created on 16/8/5.
 */
public class MockInputStream extends ServletInputStream {
    private InputStream srcInputStream;

    public MockInputStream(InputStream srcInputStream) {
        this.srcInputStream = srcInputStream;
    }

    @Override
    public int read() throws IOException {
        return srcInputStream.read();
    }
}
