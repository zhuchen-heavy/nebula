package com.nebula.web.aop;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @UserAccess
    @Override
    public void testIntroduction() {
        System.out.println("do testIntroduction");
    }
}
