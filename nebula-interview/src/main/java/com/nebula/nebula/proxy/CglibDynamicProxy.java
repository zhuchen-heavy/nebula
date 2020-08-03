package com.nebula.nebula.proxy;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * <p>
 * cglib动态代理：代码参考：https://www.jianshu.com/p/b25cba7e438
 * <p>
 * 注：需要引入cglib的包，本次直接引入aop的包。
 * </p>
 *
 * @author: zhu.chen
 * @date: 2020/8/3
 * @version: v1.0.0
 */
public class CglibDynamicProxy {

    public static void main(String[] args) {
        // 生成运行中的字节码
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\git_workplace\\nebula");
        CglibMethodInterceptor interceptor = new CglibMethodInterceptor();
        UserService userService = (UserService) interceptor.getInstance(new UserService());
        userService.addUser();
    }

    public static class UserService {

        public void addUser() {
            System.out.println("增加一个用户。。。");
        }

    }

    public static class CglibMethodInterceptor implements MethodInterceptor {

        private Object target;

        public Object getInstance(Object target) {
            this.target = target;
            Enhancer enhancer = new Enhancer();
            // 设置父类
            enhancer.setSuperclass(this.target.getClass());
            // 设置回调方法 ---> 将当前的MethodInterceptor设置为回调
            enhancer.setCallback(this);
            // 创建代理对象
            return enhancer.create();
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("开始");
            Object result = methodProxy.invokeSuper(o, objects);
            System.out.println("结束");
            return result;
        }
    }

}
