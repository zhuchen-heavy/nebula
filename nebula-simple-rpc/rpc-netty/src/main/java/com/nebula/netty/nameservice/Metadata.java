package com.nebula.netty.nameservice;

import java.net.URI;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  注册中心的元数据区
 * </p>
 * @author: zhu.chen
 * @date: 2020/7/22
 * @version: v1.0.0
 */
public class Metadata extends HashMap<String/*服务名*/, List<URI>/*服务提供者URI列表*/> {

    private static final long serialVersionUID = -6026321977192504921L;

    /**
     * 示例：
     * Metadata:
     * 	Classname: service1
     * 	URIs:
     * 		1
     * 		2
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Metadata:").append("\n");
        for (Entry<String, List<URI>> entry : entrySet()) {
            // \t：指的就是一个tab键
            sb.append("\t").append("Classname: ").append(entry.getKey()).append("\n");
            sb.append("\t").append("URIs:").append("\n");
            for (URI uri : entry.getValue()) {
                sb.append("\t\t").append(uri).append("\n");
            }
        }
        return sb.toString();
    }

}
