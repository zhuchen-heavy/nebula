package com.nebula.design.factory.general;

/**
 * <p>
 * 静态工厂模式 Todo 普通工厂模式中一般选择这种，简单方便。缺点：违背了开闭原则
 * </p>
 * @author: zhu.chen
 * @date: 2019-09-04
 */
public class SendFactory3 {

    public static Sender produceSms(){
        return new SmsSender();
    }

    public static Sender produceMail(){
        return new MailSender();
    }

}
