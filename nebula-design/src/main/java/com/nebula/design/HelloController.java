package com.nebula.design;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 当starfish.interface.enabled的值为true时，初始化HelloController这个Bean
 * <h>
 * 应用在框架层，扩展性高
 * </h>
 * </p>
 * @author: zhu.chen
 * @date: 2019-09-28
 */
@ConditionalOnProperty(name = "starfish.interface.enabled", havingValue = "true")
@RestController
public class HelloController {

    @GetMapping("${starfish.interface.routing}")
    public String hello(){
        return "hello world";
    }

}
