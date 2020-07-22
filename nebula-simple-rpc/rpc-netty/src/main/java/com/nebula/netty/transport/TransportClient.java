package com.nebula.netty.transport;

import java.io.Closeable;
import java.net.SocketAddress;
import java.util.concurrent.TimeoutException;

/**
 * <p>
 * 客户端
 * </p>
 * @author: zhu.chen
 * @date: 2020/7/22
 * @version: v1.0.0
 */
public interface TransportClient extends Closeable {

    /**
     * 创建连接
     * @param address
     * @param connectionTimeout
     * @return
     * @throws InterruptedException
     * @throws TimeoutException
     */
    Transport createTransport(SocketAddress address, long connectionTimeout) throws InterruptedException, TimeoutException;

    @Override
    void close();
}
