package com.nebula.lambdademo.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;

//@Configuration
public class AppListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    @Autowired
    private Person person;

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        System.out.println(person.getName());
    }

}
