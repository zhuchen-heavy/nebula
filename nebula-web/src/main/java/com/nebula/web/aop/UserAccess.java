package com.nebula.web.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * aop也能对注解进行编织
 * </p>
 * @author: zhu.chen
 * @date: 2019-12-28
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UserAccess {

    String desc() default "无信息";

}
