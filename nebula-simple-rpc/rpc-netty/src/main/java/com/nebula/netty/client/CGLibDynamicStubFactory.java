package com.nebula.netty.client;

import com.nebula.netty.transport.Transport;

/**
 * <p>
 *  使用cglib来动态代理
 * </p>
 * @author: zhu.chen
 * @date: 2020/7/22
 * @version: v1.0.0
 */
public class CGLibDynamicStubFactory implements StubFactory {

    @Override
    public <T> T createStub(Transport transport, Class<T> serviceClass) {
        try {
            CGLibDynamicProxy proxy = new CGLibDynamicProxy(serviceClass);
            proxy.setTransport(transport);
            return (T) proxy.getProxy();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
