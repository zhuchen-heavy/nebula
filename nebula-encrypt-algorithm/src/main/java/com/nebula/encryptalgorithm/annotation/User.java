package com.nebula.encryptalgorithm.annotation;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

@Data
public class User {

    //使用自定义注解
    //@MyConstraint
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    private Integer id;

//    @Past(message = "生日不能是之后的日期")
//    private Date birthday;

}
