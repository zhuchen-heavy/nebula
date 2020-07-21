package com.nebula.design.decorator;

/**
 * <p>
 * 装饰者
 * </p>
 *
 * @author: zhu.chen
 * @date: 2019-09-08
 */
public class Decorator implements Sourceable {

    private Sourceable sourceable;

    public Decorator(Sourceable sourceable) {
        super();
        this.sourceable = sourceable;
    }

    @Override
    public void method() {
        System.out.println("before decorator!");
        sourceable.method();
        System.out.println("after decorator!");
    }

}
