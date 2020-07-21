package com.nebula.encryptalgorithm.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 自定义注解
 * </p>
 * @author: zhu.chen
 * @date: 2019-05-18 
 */
@Constraint(validatedBy = MyConstraintValidator.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyConstraint {

    String message() default "小朱自定义注解";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
