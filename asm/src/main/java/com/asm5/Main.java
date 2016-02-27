package com.asm5;

/**
 * Created by Kindevil on 2016/2/27.
 */
public class Main {
    public static void main(String[] args) {
        ClassWriter cw = new ClassWriter(0);
        TraceClassVisitor cv = new TraceClassVisitor(cw, new PrintWriter(System.out));
    }
}
