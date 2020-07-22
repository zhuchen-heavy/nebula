package com.nebula.api.spi;

import java.util.Collection;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * <p>
 *  spi实现工具类
 * </p>
 * @author: zhu.chen
 * @date: 2020/7/22
 * @version: v1.0.0
 */
public class ServiceSupport {

    private final static Map<String, Object> singletonServices = new ConcurrentHashMap<>();

    /**
     * 找spi实现里的第一个
     * @param service
     * @param <S>
     * @return
     */
    public synchronized static <S> S load(Class<S> service) {
        return StreamSupport.stream(ServiceLoader.load(service).spliterator(), false).map(ServiceSupport::singletonFilter).findFirst().orElseThrow(ServiceLoadException::new);
    }

    /**
     * 找spi实现里的所有
     * @param service
     * @param <S>
     * @return
     */
    public synchronized static <S> Collection<S> loadAll(Class<S> service) {
        return StreamSupport.stream(ServiceLoader.load(service).spliterator(), false).map(ServiceSupport::singletonFilter).collect(Collectors.toList());
    }


    private static <S> S singletonFilter(S service) {
        if (service.getClass().isAnnotationPresent(Singleton.class)) {
            String className = service.getClass().getCanonicalName();
            Object singletonInstance = singletonServices.putIfAbsent(className, service);
            return singletonInstance == null ? service : (S) singletonInstance;
        } else {
            return service;
        }
    }

}
