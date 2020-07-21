package com.nebula.web.command;

import com.nebula.web.aop.DoSthService;
import com.nebula.web.aop.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestCommand implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private DoSthService doSthService;

    @Override
    public void run(String... args) throws Exception {
        userService.testIntroduction();
        //doSthService.doSth();
    }

}
