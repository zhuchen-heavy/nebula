/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nebula.netty.client.stub;


import com.nebula.netty.client.RequestIdSupport;
import com.nebula.netty.client.ServiceStub;
import com.nebula.netty.client.ServiceTypes;
import com.nebula.netty.serialize.SerializeSupport;
import com.nebula.netty.transport.Transport;
import com.nebula.netty.transport.command.Code;
import com.nebula.netty.transport.command.Command;
import com.nebula.netty.transport.command.Header;
import com.nebula.netty.transport.command.ResponseHeader;

import java.util.concurrent.ExecutionException;

/**
 * @author: zhu.chen
 * @date: 2020/7/22
 * @version: v1.0.0
 */
public abstract class AbstractStub implements ServiceStub {

    protected Transport transport;

    /**
     * 动态生成的class类会调用到：DynamicStubFactory#createStub
     *
     * package com.nebula.netty.client.stub;
     * import com.nebula.netty.serialize.SerializeSupport;
     *
     * public class HelloServiceStub extends AbstractStub implements com.nebula.service.HelloService {
     *     @Override
     *     public String hello(String arg) {
     *         return SerializeSupport.parse(
     *                 invokeRemote(
     *                         new RpcRequest(
     *                                 "com.nebula.service.HelloService",
     *                                 "hello",
     *                                 SerializeSupport.serialize(arg)
     *                         )
     *                 )
     *         );
     *     }
     * }
     */
    protected byte[] invokeRemote(RpcRequest request) {
        Header header = new Header(ServiceTypes.TYPE_RPC_REQUEST, 1, RequestIdSupport.next());
        byte[] payload = SerializeSupport.serialize(request);
        Command requestCommand = new Command(header, payload);
        try {
            Command responseCommand = transport.send(requestCommand).get();
            ResponseHeader responseHeader = (ResponseHeader) responseCommand.getHeader();
            if (responseHeader.getCode() == Code.SUCCESS.getCode()) {
                return responseCommand.getPayload();
            } else {
                throw new Exception(responseHeader.getError());
            }

        } catch (ExecutionException e) {
            throw new RuntimeException(e.getCause());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setTransport(Transport transport) {
        this.transport = transport;
    }
}
