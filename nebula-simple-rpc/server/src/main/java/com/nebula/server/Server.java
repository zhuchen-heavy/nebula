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
package com.nebula.server;

import com.nebula.api.NameService;
import com.nebula.api.RpcAccessPoint;
import com.nebula.api.spi.ServiceSupport;
import com.nebula.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.File;
import java.net.URI;

/**
 * @author: zhu.chen
 * @date: 2020/7/22
 * @version: v1.0.0
 */
public class Server {

    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) throws Exception {
        String serviceName = HelloService.class.getCanonicalName();
        File tmpDirFile = new File(System.getProperty("java.io.tmpdir"));
        // 本地使用一个简单兜底文件作为注册中心
        File file = new File(tmpDirFile, "simple_rpc_name_service.data");
        HelloService helloService = new HelloServiceImpl();
        logger.info("创建并启动RpcAccessPoint...");
        try (RpcAccessPoint rpcAccessPoint = ServiceSupport.load(RpcAccessPoint.class); Closeable ignored = rpcAccessPoint.startServer()) {
            NameService nameService = rpcAccessPoint.getNameService(file.toURI());
            assert nameService != null;
            logger.info("向RpcAccessPoint注册{}服务...", serviceName);
            URI uri = rpcAccessPoint.addServiceProvider(helloService, HelloService.class);
            logger.info("服务名: {}, 向NameService注册...", serviceName);
            nameService.registerService(serviceName, uri);
            logger.info("开始提供服务，按任何键退出.");
            //noinspection ResultOfMethodCallIgnored
            System.in.read();
            logger.info("Bye!");
        }
    }

}
