package com.nebula.web.aop;

import org.springframework.stereotype.Service;

@Service
public class DoSthServiceImpl implements DoSthService {
    @Override
    public void doSth() {
        System.out.println("do sth ....");
    }
}
