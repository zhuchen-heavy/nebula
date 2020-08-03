package com.nebula.nebula.proxy;

/**
 * <p>
 * 静态代理
 * </p>
 *
 * @author: zhu.chen
 * @date: 2020/8/3
 * @version: v1.0.0
 */
public class StaticProxy {

    public static void main(String[] args) {
        IDoSomething iDoSomething = new SingProxy();
        System.out.println(iDoSomething.doSomething(3));
    }

    public interface IDoSomething {

        int doSomething(int num);

    }

    /**
     * 被代理类
     */
    public static class Sing implements IDoSomething {

        @Override
        public int doSomething(int num) {
            System.out.println("Sing a song");
            return num;
        }

    }

    public static class SingProxy implements IDoSomething {

        private IDoSomething sing = new Sing();

        @Override
        public int doSomething(int num) {
            System.out.println("Befor singing ");
            int sum = sing.doSomething(num);
            System.out.println("After singing ");
            return sum;
        }

    }

}
