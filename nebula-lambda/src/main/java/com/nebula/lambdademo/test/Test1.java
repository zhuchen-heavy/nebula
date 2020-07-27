package com.nebula.lambdademo.test;

import java.util.Arrays;
import java.util.function.Consumer;

public class Test1 {

    public static void main(String[] args) {
        Consumer<String> greeter = (p) -> System.out.println("Hello");
        System.out.println(greeter.getClass().getName());
        Arrays.asList().parallelStream();
    }

}
