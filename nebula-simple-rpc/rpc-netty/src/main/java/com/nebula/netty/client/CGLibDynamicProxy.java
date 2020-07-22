package com.nebula.netty.client;

import com.nebula.netty.client.stub.AbstractStub;
import com.nebula.netty.client.stub.RpcRequest;
import com.nebula.netty.serialize.SerializeSupport;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLibDynamicProxy extends AbstractStub implements MethodInterceptor {

    private Class clazz;

    public CGLibDynamicProxy(Class clazz) {
        this.clazz = clazz;
    }

    private Enhancer enhancer = new Enhancer();

    public Object getProxy() {
        //设置需要创建子类的类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        //通过字节码技术动态创建子类实例
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        return SerializeSupport.parse(invokeRemote(new RpcRequest(clazz.getCanonicalName(), method.getName(), SerializeSupport.serialize(objects))));
    }

}
