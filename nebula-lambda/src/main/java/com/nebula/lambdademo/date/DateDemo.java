package com.nebula.lambdademo.date;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * <p>
 * 1.8的日期
 * 参考示例：https://www.jianshu.com/p/2949db9c3df5
 * </p>
 *
 * @author: zhu.chen
 * @date: 2020/7/24
 * @version: v1.0.0
 */
public class DateDemo {

    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        // 当前时间：2020-07-24
        System.out.println(today);
        // 2020-07-01
        System.out.println(today.with(TemporalAdjusters.firstDayOfMonth()));
        // 2020-07-01
        System.out.println(today.withDayOfMonth(1));
        // 2020-07-31
        System.out.println(today.with(TemporalAdjusters.lastDayOfMonth()));
        // 2020-07-25
        System.out.println(today.plusDays(1));
        // 判断是否为闰年 true
        System.out.println(today.isLeapYear());

        // 判断当前日期是否为生日
        LocalDate birthday = LocalDate.of(1994, 3, 3);
        MonthDay birthdayMd = MonthDay.of(birthday.getMonth(), birthday.getDayOfMonth());
        MonthDay todayMd = MonthDay.from(LocalDate.of(2020, 7, 14));
        System.out.println(birthdayMd.equals(todayMd));

        // 1：获取当前日期
        LocalTime nowTime = LocalTime.now();
        // 10:37:50.347
        System.out.println(nowTime);
        // 10:37:50
        System.out.println(LocalTime.now().withNano(0));
        //14:10:21
        System.out.println(LocalTime.of(14, 10, 21));
        // 12:00:01
        System.out.println(LocalTime.parse("12:00:01"));
        // 12:37:50.347
        System.out.println(nowTime.plusHours(2));
        // 12:37:50.347
        System.out.println(nowTime.plus(2, ChronoUnit.HOURS));
    }

}
