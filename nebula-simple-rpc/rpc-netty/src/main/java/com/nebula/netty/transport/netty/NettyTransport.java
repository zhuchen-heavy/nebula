/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nebula.netty.transport.netty;

import com.nebula.netty.transport.InFlightRequests;
import com.nebula.netty.transport.ResponseFuture;
import com.nebula.netty.transport.Transport;
import com.nebula.netty.transport.command.Command;
import io.netty.channel.Channel;

import java.util.concurrent.CompletableFuture;

/**
 * <p>
 * Netty传输实现
 * </p>
 * @author: zhu.chen
 * @date: 2020/7/22
 * @version: v1.0.0
 */
public class NettyTransport implements Transport {

    private final Channel channel;

    private final InFlightRequests inFlightRequests;

    public NettyTransport(Channel channel, InFlightRequests inFlightRequests) {
        this.channel = channel;
        this.inFlightRequests = inFlightRequests;
    }

    @Override
    public CompletableFuture<Command> send(Command request) {
        // netty的数据处理完会回写到CompletableFuture中。
        CompletableFuture<Command> completableFuture = new CompletableFuture<>();
        try {
            inFlightRequests.put(new ResponseFuture(request.getHeader().getRequestId(), completableFuture));
            channel.writeAndFlush(request).addListener(channelFuture -> {
                if (!channelFuture.isSuccess()) {
                    completableFuture.completeExceptionally(channelFuture.cause());
                    channel.close();
                }
            });

        } catch (Exception e) {
            // 处理发送异常
            inFlightRequests.remove(request.getHeader().getRequestId());
            completableFuture.completeExceptionally(e);
        }
        return completableFuture;
    }

}
