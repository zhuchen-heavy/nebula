package com.nebula.lambdademo.test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Test5 {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream().filter(i -> i % 2 == 0).distinct()
        .forEach(System.out::println);

        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(5);
        set.add(4);
        set.add(3);
        System.out.println(set);
        set.stream().limit(2).collect(Collectors.toList());

    }

}
