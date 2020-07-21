package com.nebula.design.factory.general;

public class FactoryTest {

    public static void main(String[] args) {
        SendFactory1 factory1 = new SendFactory1();
        Sender sender1 = factory1.produce("sms");
        sender1.send();

        SendFactory2 factory2 = new SendFactory2();
        Sender sender2 = factory2.produceMail();
        sender2.send();

        Sender sender3 = SendFactory3.produceMail();
        sender3.send();
    }

}
