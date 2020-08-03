package com.nebula.design.singleton;

/**
 * <p>
 * 单例的实现 ---> 静态内部类
 *
 * 原理：根据类加载的原理，"当遇到访问静态字段的指令时，初始化该静态字段所在的类".
 * 类只会被加载1次，类加载线程安全。
 *
 * </p>
 *
 * @author: zhu.chen
 * @date: 2020/7/29
 * @version: v1.0.0
 */
public class Singleton {

    private Singleton() {
    }

    public static Singleton getInstance() {
        return Lazy.singleton;
    }

    private static final class Lazy {
        private static Singleton singleton = new Singleton();
    }

}
