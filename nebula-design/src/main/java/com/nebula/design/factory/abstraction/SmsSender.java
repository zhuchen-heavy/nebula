package com.nebula.design.factory.abstraction;

public class SmsSender implements Sender {

    @Override
    public void send() {
        System.out.println("this is sms sender!");
    }

}
