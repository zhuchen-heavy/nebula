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
package com.nebula.netty.transport.command;

/**
 * <p>
 *  请求的封装体
 * </p>
 * @author: zhu.chen
 * @date: 2020/7/22
 * @version: v1.0.0
 */
public class Command {
    protected Header header;
    // payload 就是命令中要传输的数据，这里我们要求这个数据已经是被序列化之后生成的字节数组。
    private byte [] payload;

    public Command(Header header, byte [] payload) {
        this.header = header;
        this.payload = payload;
    }
    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public byte [] getPayload() {
        return payload;
    }

    public void setPayload(byte [] payload) {
        this.payload = payload;
    }
}
