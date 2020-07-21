package com.nebula.design.decorator;

/**
 * <p>
 * 被装饰类
 * </p>
 * @author: zhu.chen
 * @date: 2019-09-08
 */
public class Source implements  Sourceable{

    @Override
    public void method() {
        System.out.println("the original method!");
    }

}
