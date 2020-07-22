package com.nebula.netty.transport;

/**
 * <p>
 * 服务端
 * </p>
 * @author: zhu.chen
 * @date: 2020/7/22
 * @version: v1.0.0
 */
public interface TransportServer {

    /**
     *
     * @param requestHandlerRegistry
     * @param port
     * @throws Exception
     */
    void start(RequestHandlerRegistry requestHandlerRegistry, int port) throws Exception;

    /**
     *
     */
    void stop();

}
