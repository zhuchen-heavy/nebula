package com.nebula.encryptalgorithm.test1;

/**
 * <p>
 * 测试class文件的加解密
 * </p>
 * @author: zhu.chen
 * @date: 2019-05-12
 */
public class Hello {

    public static void main(String[] args) {
        System.out.println("hello world");
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getPath());
        System.out.println(Hello.class.getResource(""));
    }

}
