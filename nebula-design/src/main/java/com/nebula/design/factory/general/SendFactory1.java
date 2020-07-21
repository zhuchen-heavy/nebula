package com.nebula.design.factory.general;

/**
 * <p>
 * 普通工厂模式
 * </p>
 * @author: zhu.chen
 * @date: 2019-09-04
 */
public class SendFactory1 {

    public Sender produce(String type){
        if ("mail".equals(type)) {
            return new MailSender();
        } else if ("sms".equals(type)) {
            return new SmsSender();
        } else {
            System.out.println("请输入正确的类型");
            return null;
        }
    }

}
