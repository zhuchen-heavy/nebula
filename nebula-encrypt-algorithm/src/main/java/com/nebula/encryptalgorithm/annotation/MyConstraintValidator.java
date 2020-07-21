package com.nebula.encryptalgorithm.annotation;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyConstraintValidator implements ConstraintValidator<MyConstraint, MultipartFile> {
    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        //初始化的时候
        System.out.println("my validator init");
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        //这里写判断逻辑
        System.out.println(value.getOriginalFilename());
        //我这里直接方法false，就是要提示错误，如果返回true就表示验证通过
        return false;
    }
}
