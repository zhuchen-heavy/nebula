package com.nebula.encryptalgorithm.annotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Validated
@RestController
@Slf4j
public class UserController {

    @PostMapping("/user")
    public User postUser(@RequestBody @Validated User user, BindingResult bs){
        if (bs.hasErrors()) {
            //打印错误
            log.info("error={}",bs.getFieldError().getDefaultMessage());
        }
        return user;
    }


    @PostMapping("/upload")
    public String upload(@RequestParam("aa") @MyConstraint MultipartFile multipartFile){
        return "success";
    }

}
