package com.nebula.design.factory.abstraction;

/**
 * <p>
 * 抽象工厂模式，具体的工厂也是面向工厂接口编程的
 * </p>
 * @author: zhu.chen
 * @date: 2019-09-04
 */
public class Test1 {

    public static void main(String[] args) {
        Provider provider = new SendMailFactory();
        Sender sender = provider.produce();
        sender.send();
    }

}
