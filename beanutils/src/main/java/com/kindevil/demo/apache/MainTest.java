package com.kindevil.demo.apache;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

import com.google.common.base.Preconditions;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.common.collect.Maps;

/**
 * @author zhangxin.zhang created on 16-4-19.
 */
public class MainTest {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        A a = new A(1);
        copy(a,new B(2),false);
        System.out.println(a);
    }

    public static void copy(Object target ,Object source ,boolean checkTarget) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        System.out.println("========");
        System.out.println(target.getClass());
        System.out.println(source.getClass());
        if(target.getClass() == source.getClass()) {
            target = source;
            return;
        }

        Class<?> srcClass = source.getClass();
        Class<?> tarClass = target.getClass();

        Map<String,Method> srcMethods =Maps.newHashMap();
        for (Method srcMethod : srcClass.getDeclaredMethods()) {
            String name = srcMethod.getName();
            if (!((name.startsWith("get") || name.startsWith("is"))
                    && !"getClass".equals(name)
                    && Modifier.isPublic(srcMethod.getModifiers())
                    && srcMethod.getParameterTypes().length == 0)) {
                continue;
            }
            int i = name.startsWith("get") ? 3 : 2;
            System.out.println(name.substring(i));
            srcMethods.put(name.substring(i),srcMethod);
        }

        for (Method tarMethod : tarClass.getDeclaredMethods()) {
            String name = tarMethod.getName();
            if (!(name.startsWith("set")
                    && Modifier.isPublic(tarMethod.getModifiers())
                    && tarMethod.getParameterTypes().length == 1)) {
                continue;
            }
            Method srcMethod = srcMethods.get(name.substring(3));
            if(checkTarget) {
                Preconditions.checkNotNull(srcMethod,"source没有target的该属性");
            } else if( null == srcMethod ) {
                continue;
            }
            Object value = srcMethod.invoke(source);
            if( null == value ) {
                continue;
            }
            if(!isPrimitive(srcMethod.getReturnType())) {
                Class<?>[] parameterTypes = tarMethod.getParameterTypes();
                Object o = parameterTypes[0].newInstance();
                copy(o, value ,checkTarget);
                tarMethod.invoke(target,o);
                continue;
            }
            tarMethod.invoke(target,value);
        }
    }

    private static boolean isPrimitive(Class<?> type) {
        return type.isPrimitive()
                || type == String.class
                || type == Character.class
                || type == Boolean.class
                || type == Byte.class
                || type == Short.class
                || type == Integer.class
                || type == Long.class
                || type == Float.class
                || type == Double.class
                || type == Object.class;
    }
}

class A {
    int i;
    String str = "a";
    C ob;
    int a;

    public void setA(int a) {
        this.a = a;
    }

    public C getOb() {
        return ob;
    }

    public void setOb(C ob) {
        this.ob = ob;
    }

    public A(int i) {
        this.i=i;
    }

    public void setI(int i){
        this.i = i;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}

class B {
    int i;
    String str = "B";
    D ob = new D();

    public D getOb() {
        return ob;
    }

    public void setOb(D ob) {
        this.ob = ob;
    }

    public B(int i) {
        this.i=i;
    }

    public int getI(){
        return i;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}

class C {
    String str = "C";

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}

class D {
    String str = "D";

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}