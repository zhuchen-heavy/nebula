package com.nebula.nebula.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>
 * Jdk动态代理
 * </p>
 *
 * @author: zhu.chen
 * @date: 2020/8/3
 * @version: v1.0.0
 */
public class JdkDynamicProxy {

    public static void main(String[] args) throws SecurityException, NoSuchMethodException {
        // 将生成的代理类保存到磁盘上。生成的路径是：/当前应用目录/com/sun/proxy/$Proxy0.class
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        DynamicProxyHandler proxyHandler = new DynamicProxyHandler(new ProxyClassImpl());
        // 代理类实例
        IProxyClass proxyClass = (IProxyClass) Proxy.newProxyInstance(IProxyClass.class.getClassLoader(), new Class[]{IProxyClass.class}, proxyHandler);
        System.out.println(proxyClass.doSomething(5));
    }

    public interface IProxyClass {

        int doSomething(int num);

    }

    /**
     * 委托类（被代理类）
     */
    public static class ProxyClassImpl implements IProxyClass {

        @Override
        public int doSomething(int num) {
            System.out.println("方法执行中.....");
            return num;
        }

    }

    /**
     * 代理处理器
     */
    public static class DynamicProxyHandler implements InvocationHandler {

        private Object proxied;

        /**
         * @param proxied 被代理对象
         */
        public DynamicProxyHandler(Object proxied) {
            this.proxied = proxied;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("method : " + method.getName());
            System.out.println("args :" + args[0].getClass().getName());
            System.out.println("Before invoke method...");
            Object object = method.invoke(proxied, args);
            System.out.println("After invoke method...");
            return object;
        }

    }

}
