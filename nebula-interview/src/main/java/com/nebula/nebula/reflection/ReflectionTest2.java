package com.nebula.nebula.reflection;

/**
 * <h>
 * 得到class类的方式
 * </h>
 *
 * @author zhu.chen
 * @version 1.0
 * @date 2020/8/2
 */
public class ReflectionTest2 {

    public static void main(String[] args) throws ClassNotFoundException {
        /**
         * 1：得到Class的方式
         */
        // 类.class
        Class clazz1 = ReflectionTest2.class;
        // 对象.getClass()
        ReflectionTest2 reflectionTest2 = new ReflectionTest2();
        Class clazz2 = reflectionTest2.getClass();
        // Class.forName()
        Class clazz3 = Class.forName("com.nebula.nebula.reflection.ReflectionTest2");
        System.out.println(clazz1);
        System.out.println(clazz2);
        System.out.println(clazz3);

        /**
         * 2：基本类型（boolean，byte，char，short，int，long，float，double）、数组、void同样表现为Class对象。
         */
        // 基本类型需要使用包装类
        Class clazz7 = int.class;
        Class clazz4 = Integer.class;
        // void
        Class clazz5 = Void.class;
        // 数组
        Class clazz6 = int[].class;

    }

}
