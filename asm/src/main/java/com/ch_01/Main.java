package com.ch_01;

import java.io.IOException;
import java.io.PrintWriter;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.util.TraceClassVisitor;

/**
 * TraceClassVisitor usage
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        ClassWriter cw = new ClassWriter(0);
        ClassReader cr = new ClassReader("com.ch_01.C");//读字节码抽象类
        TraceClassVisitor cv = new TraceClassVisitor(cw, new PrintWriter(System.out));//用TraceClassVisitor包装ClassWriter
        cr.accept(cv, 0);//调用ClassReader读目标类,然后用TraceClassVisitor修改
    }
}