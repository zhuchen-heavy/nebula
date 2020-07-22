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
package com.nebula.netty.transport;

import com.nebula.netty.transport.command.Command;

/**
 * <p>
 * 请求处理器
 * </p>
 * @author: zhu.chen
 * @date: 2020/7/22
 * @version: v1.0.0
 */
public interface RequestHandler {

    /**
     * 处理请求
     * @param requestCommand 请求命令
     * @return 响应命令
     */
    Command handle(Command requestCommand);

    /**
     * 支持的请求类型
     * @return
     */
    int type();
}
