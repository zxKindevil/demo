package com.dubbo.test.generate.bytecode;

/**
 * @author zhangxin
 *         Created on 18/7/13.
 */
public class Wrapper1 extends com.alibaba.dubbo.common.bytecode.Wrapper {
    public static String[] pns;
    public static java.util.Map pts;
    public static String[] mns;
    public static String[] dmns;
    public static Class[] mts0;

    public String[] getPropertyNames() {
        return pns;
    }

    public Class getPropertyType(String n) {
        return (Class) pts.get(n);
    }

    public boolean hasProperty(String n) {
        return pts.containsKey(n);
    }

    public String[] getMethodNames() {
        return mns;
    }

    public String[] getDeclaredMethodNames() {
        return dmns;
    }

    public void setPropertyValue(Object o, String n, Object v) {
        com.dubbo.test.service.impl.HelloServiceImpl w;
        try {
            w = ((com.dubbo.test.service.impl.HelloServiceImpl) o);
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
        throw new com.alibaba.dubbo.common.bytecode.NoSuchPropertyException("Not found property \"" + n + "\" filed or setter method in class com.dubbo.test.service.impl.HelloServiceImpl.");
    }

    public Object getPropertyValue(Object o, String n) {
        com.dubbo.test.service.impl.HelloServiceImpl w;
        try {
            w = ((com.dubbo.test.service.impl.HelloServiceImpl) o);
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
        throw new com.alibaba.dubbo.common.bytecode.NoSuchPropertyException("Not found property \"" + n + "\" filed or setter method in class com.dubbo.test.service.impl.HelloServiceImpl.");
    }

    public Object invokeMethod(Object o, String n, Class[] p, Object[] v) throws java.lang.reflect.InvocationTargetException {
//        com.dubbo.test.service.impl.HelloServiceImpl w;
//        try {
//            w = ((com.dubbo.test.service.impl.HelloServiceImpl) $1);
//        } catch (Throwable e) {
//            throw new IllegalArgumentException(e);
//        }
//        try {
//            if ("say".equals($2) && $3.length == 0) {
//                return ($w) w.say();
//            }
//        } catch (Throwable e) {
//            throw new java.lang.reflect.InvocationTargetException(e);
//        }
//        throw new com.alibaba.dubbo.common.bytecode.NoSuchMethodException("Not found method \"" + $2 + "\" in class com.dubbo.test.service.impl.HelloServiceImpl.");

        return null;
    }

}
