package com.nebula.design.factory.general;

/**
 * <p>
 * 多个工厂模式
 * </p>
 * @author: zhu.chen
 * @date: 2019-09-04
 */
public class SendFactory2 {

    public Sender produceSms(){
        return new SmsSender();
    }

    public Sender produceMail(){
        return new MailSender();
    }

}
