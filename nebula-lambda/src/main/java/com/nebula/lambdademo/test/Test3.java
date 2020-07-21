package com.nebula.lambdademo.test;

import java.util.Arrays;
import java.util.List;

public class Test3 {

    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("a1", "a2", "b1", "c1", "c2", "c4", "c3");
        stringList.stream().filter(s -> s.startsWith("c")).map(String::toUpperCase).forEach(
                System.out::println
        );

        System.out.println("--------------------");

        stringList.stream().filter(s -> s.startsWith("c")).map(String::toUpperCase).forEach(
              n -> System.out.println(n)
        );

    }

}
