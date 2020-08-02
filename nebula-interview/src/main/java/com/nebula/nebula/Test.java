package com.nebula.nebula;

import java.security.ProtectionDomain;

/**
 * <h>
 * 得到class类的方式
 * </h>
 *
 * @version 1.0
 * @author zhu.chen
 * @date 2020/8/2
 */
public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        /**
         * 1：得到Class的方式
         */
        // 类.class
        Class clazz1 = Test.class;
        // 对象.getClass()
        Test test = new Test();
        Class clazz2 = test.getClass();
        // Class.forName()
        Class clazz3 = Class.forName("com.nebula.nebula.Test");
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
