package com.nebula.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * <p>
 * CompletionService：
 * 使用场景：
 * 当向Executor提交多个任务并且希望获得它们在完成之后的结果，如果用FutureTask，可以循环获取task，并调用get方法去获取task执行结果，
 * 但是如果task还未完成，获取结果的线程将阻塞直到task完成，由于不知道哪个task优先执行完毕，使用这种方式效率不会很高。
 * 在jdk5时候提出接口CompletionService，它整合了Executor和BlockingQueue的功能，可以更加方便在多个任务执行时获取到任务执行结果。
 * <p>
 * CompletionService会将执行完的result先放到阻塞队列中，因此基于CompletionService的get()拿到的总是先执行完的result。
 * </p>
 *
 * @author: zhu.chen
 * @date: 2020/7/23
 * @version: v1.0.0
 */
public class CompletionServiceDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 需求：当向Executor提交多个任务并且希望获得它们在完成之后的结果
        // 1：使用原生的Executor
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Future<Integer>> futureList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            int a = i;
            futureList.add(executorService.submit(() -> {
                return a + a;
            }));
        }
        for (Future<Integer> future : futureList) {
            System.out.println(future.get());
        }
        executorService.shutdown();

        System.out.println("------------------------");
        // 1：使用CompletionService：CompletionService = Executor + BlockingQueue.
        ExecutorService pool = Executors.newFixedThreadPool(3);
        CompletionService<Integer> completionService = new ExecutorCompletionService(pool);
        for (int i = 0; i < 3; i++) {
            int a = i;
            completionService.submit(() -> {
                return a + a;
            });
        }
        for (int i = 0; i < 3; i++) {
            System.out.println(completionService.take().get());
        }
        pool.shutdown();
    }
}
