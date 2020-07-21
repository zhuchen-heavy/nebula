package com.nebula.design.factory.general;

public class MailSender implements Sender {

    @Override
    public void send() {
        System.out.println("this is mail sender!");
    }

}
