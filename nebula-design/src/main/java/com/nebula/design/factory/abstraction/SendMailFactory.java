package com.nebula.design.factory.abstraction;

public class SendMailFactory implements Provider {

    @Override
    public Sender produce() {
        return new MailSender();
    }

}
