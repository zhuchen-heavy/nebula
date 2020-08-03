package com.nebula.nebula.reflection;

import java.lang.reflect.Method;

/**
 * <p>
 * method.invoke 核心
 * </p>
 *
 * @author: zhu.chen
 * @date: 2020/7/31
 * @version: v1.0.0
 */
public class ReflectionTest1 {

    public static void target(int i) {
        new Exception("#" + i).printStackTrace();
    }

    public static void main(String[] args) throws Exception {
        Class klass = ReflectionTest1.class;
        Method method = klass.getMethod("target", int.class);
        method.invoke(null, 0);
    }
}
