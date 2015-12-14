package com.kindevil.demo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Method[] methods = Bean.class.getMethods();
        System.out.println(methods);
        methods[0].invoke(Bean.class.newInstance());
    }
}
