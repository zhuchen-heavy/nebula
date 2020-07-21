package com.nebula.nebulautils.log;

import org.slf4j.MDC;

public class MDCTest {

    public static void main(String[] args) {
        MDC.put("name", "zhangsan");
        String name = MDC.get("name");
        // zhangsan
        System.out.println(name);

        ThreadLocal<String> threadLocal = new ThreadLocal();
        threadLocal.set("zhangsan");
        String value = threadLocal.get();
        // zhangsan
        System.out.println(value);

    }

}
