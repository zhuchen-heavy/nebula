package com.nebula.design.factory.abstraction;

public class SendSmsFactory implements Provider {

    @Override
    public Sender produce() {
        return new SmsSender();
    }

}
