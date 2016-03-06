package com.asm5;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.PrintWriter;

/**
 * Created by Kindevil on 2016/2/27.
 */
public class Main {
    public static void main(String[] args) {
        ClassWriter cw = new ClassWriter(0);
        TraceClassVisitor cv = new TraceClassVisitor(cw, new PrintWriter(System.out));
    }
}
