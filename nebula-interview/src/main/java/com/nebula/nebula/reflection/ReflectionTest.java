package com.nebula.nebula.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <p>
 * Java反射 --> 有参构造和无参构造
 * </p>
 *
 * @author: zhu.chen
 * @date: 2020/7/31
 * @version: v1.0.0
 */
public class ReflectionTest {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        // 1：使用clazz.newInstance()来构造反射，则反射的类时必须有无参构造
        Class clazz = Test.class;
        Test test = (Test) clazz.newInstance();
        Field field = clazz.getDeclaredField("name");
        field.setAccessible(true);
        field.set(test, "zhangsan");
        Method method = clazz.getMethod("welcome");
        Object value = method.invoke(test);
        System.out.println(value);

        // 2：使用clazz1.getConstructor来构造反射，可以使用有参构造
        Class clazz1 = Test.class;
        Constructor constructor = clazz1.getConstructor(String.class);
        Test test1 = (Test) constructor.newInstance("xiaoming");
        Method method1 = clazz1.getMethod("welcome");
        Object value1 = method1.invoke(test1);
        System.out.println(value1);
    }

    private static class Test {

        private String name;

        public Test() {
        }

        public Test(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public String welcome() {
            return "hello " + name;
        }
    }
}
