package com.nebula.design.decorator;

public class DecoratorTest {

    public static void main(String[] args) {
        Sourceable sourceable = new Source();
        Sourceable sourceable1 = new Decorator(sourceable);
        sourceable1.method();
    }

}
