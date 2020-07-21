package com.nebula.lambdademo.test;

import com.nebula.lambdademo.entity.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Test2 {

    private static List<Apple> apples = Arrays.asList(new Apple(100, "red"),
            new Apple(101, "green"), new Apple(132, "green"),
            new Apple(90, "green"), new Apple(122, "red")
    );

    public static void main (String[] args) {
        List<Apple> isGreenApple = apples.stream().filter(apple -> "green".equals(apple.getColor())).collect(Collectors.toList());
        System.out.println(isGreenApple);


        // 筛选出绿色苹果
        List<Apple> greenApples = filterApples(apples, Test2::isGreenApple);
        System.out.println(greenApples);

        // 筛选重量大于120克的苹果
        List<Apple> heavyApples = filterApples(apples, Test2::isHeavyApple);
        System.out.println(heavyApples);
    }

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 120;
    }

    public static List<Apple> filterApples(List<Apple> apples, Predicate<Apple> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

}
