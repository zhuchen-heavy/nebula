package com.nebula.spring.mvc.async;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 测试spring mvc的异步响应
 * </p>
 *
 * @author: zhu.chen
 * @date: 2020/7/24
 * @version: v1.0.0
 */
@RestController
public class AsyncController {

    private Map<String, DeferredResult<String>> deferredResultMap = new ConcurrentHashMap<>();

    /**
     * spring mvc的同步响应
     * 业务执行的线程为：tomcat的业务线程。
     */
    @GetMapping("/sync")
    public String sync() {
        // 线程名：http-nio-8080-exec-1
        System.out.println("线程名：" + Thread.currentThread().getName());
        return "hello world";
    }


    /**
     * 不论是Callable异或DeferredResult，都是通过释放容器线程，通过另外一个副线程执行逻辑的形式来扩展系统的吞吐能力。
     */

    /**
     * spring mvc的异步响应
     * 业务执行的线程为：spring中的TaskExecutor。
     * <p>
     * 原理：
     * 处理流程大致为Spring会启动一个线程去将Callable交给TaskExecutor去处理，然后 DispatcherServlet 退出主线程，
     * 同时把 Response 保持打开状态，等到 Callable 处理完成，SpringMvc会重新分配一个Request 请求，
     * DispatcherServlet 重新调用和Callable处理的返回结果值，然后返回视图。
     */
    @GetMapping("/asyncHello")
    public Callable<String> asyncHello() {
        return () -> {
            // 线程名：task-1
            System.out.println("线程名：" + Thread.currentThread().getName());
            return "hello world";
        };
    }

    /**
     * DeferredResult 的执行线程是由我们（程序员）控制的，只需要将结果set进去即可。
     * 若没有将值set到DeferredResult中，则该请求会被hang住。apollo的服务端推送配置就是基于该思想的。
     * <p>
     * 访问方式：
     * 1：先访问http://localhost:8080/asyncDeferred，请求hang住
     * 2：再访问http://localhost:8080/trigger，请求1即可结束。
     */
    @GetMapping("/asyncDeferred")
    public DeferredResult<String> asyncDeferred() {
        // 超时3s：DeferredResult可以设置超时时间，且设置超时后的处理
        //DeferredResult<String> deferredResult = new DeferredResult<String>(5000L);
        //deferredResult.setErrorResult("超时啦！");
        DeferredResult<String> deferredResult = new DeferredResult<>();
        deferredResultMap.put("async", deferredResult);
        return deferredResultMap.get("async");
    }

    @GetMapping("/trigger")
    public String triggerDeferredResult() {
        DeferredResult<String> deferredResult = deferredResultMap.get("async");
        deferredResult.setResult("hello world");
        return "ok";
    }

}
