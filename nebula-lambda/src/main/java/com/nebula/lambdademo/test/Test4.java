package com.nebula.lambdademo.test;

import com.nebula.lambdademo.entity.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test4 {

    /**
     * 菜单
     */
    public static final List<Dish> MENU =
            Arrays.asList(new Dish("pork", false, 800),
                    new Dish("beef", false, 700),
                    new Dish("chicken", false, 400),
                    new Dish("french fries", true, 530),
                    new Dish("rice", true, 350),
                    new Dish("season fruit", true, 120),
                    new Dish("pizza", true, 550),
                    new Dish("prawns", false, 400),
                    new Dish("salmon", false, 450));


    public static void main(String[] args) {
        List<Dish> dishes = MENU.stream().filter(Dish::isOk).collect(Collectors.toList());
        System.out.println(dishes);
    }

}
