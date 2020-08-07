package com.nebula.nebula.basis;


import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 方法返回多个返回值
 * </p>
 *
 * @author: zhu.chen
 * @date: 2020/8/6
 * @version: v1.0.0
 */
public class MultiReturnTest {

    public static void main(String[] args) {
        // Pair
        System.out.println(test3().getLeft());
        System.out.println(test3().getRight());
        // Triple
        System.out.println(test4().getLeft());
        System.out.println(test4().getMiddle());
        System.out.println(test4().getRight());
    }

    // 1：使用对象封装

    /**
     * 2：使用List来封装
     *
     * @return
     */
    public static List<String> test1() {
        return null;
    }

    /**
     * 3：使用Map来封装
     *
     * @return
     */
    public static Map<String, String> test2() {
        return null;
    }

    /**
     * apache lang3包下的Pair
     * 返回2个元素
     *
     * @return
     */
    public static Pair<String, String> test3() {
        return Pair.of("1", "2");
    }

    /**
     * apache lang3包下的Triple
     * 返回3个元素
     *
     * @return
     */
    public static Triple<String, String, String> test4() {
        return Triple.of("1", "2", "3");
    }

}
