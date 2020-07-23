package com.nebula.juc;

import java.util.concurrent.*;

/**
 * <p>
 * CompletableFuture 的示例用法
 * 提供了非常强大的Future的扩展功能，可以帮助我们简化异步编程的复杂性，提供了函数式编程的能力，
 * 可以通过回调的方式处理计算结果，并且提供了转换和组合CompletableFuture的方法。
 * </p>
 *
 * @author: zhu.chen
 * @date: 2020/7/23
 * @version: v1.0.0
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * 1：future：jdk1.5提出，同步编程。
         * 需要将runnable、callable扔到线程池中执行，然后得到future。
         *
         * future的局限性
         * 1：不能手动完成。当你写了一个函数，用于通过一个远程API获取一个电子商务产品最新价格。因为这个 API 太耗时，你把它允许在一个独立的线程中，并且从你的函数中返回一个 Future。
         *    现在假设这个API服务宕机了，这时你想通过该产品的最新缓存价格手工完成这个Future 。你会发现无法这样做。
         * 2：不能给入回调函数：当future结束的时无法进行告知。
         * 3：多个 Future 不能串联在一起组成链式调用：有时候你需要执行一个长时间运行的计算任务，并且当计算任务完成的时候，你需要把它的计算结果发送给另外一个长时间运行的计算任务等等。
         * 4：不能组合多个 Future 的结果 假设你有10个不同的Future，你想并行的运行，然后在它们运行未完成后运行一些函数。你会发现你也无法使用 Future 这样做。
         * 5：没有异常处理 Future API 没有任务的异常处理结构。
         */
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> future = executorService.submit(() -> {
            return 100;
        });
        System.out.println(future.get());
        executorService.shutdown();

        /**
         * 示例参考：https://www.jianshu.com/p/2c9fdc27453e
         * 2：CompletableFuture：jdk1.8提出，异步编程。
         * CompletableFuture底层已经封装了线程池。参考：private static final Executor asyncPool = useCommonPool ? ForkJoinPool.commonPool() : new ThreadPerTaskExecutor();
         * 当前电脑均是多核cpu，因此会使用 ForkJoinPool。
         */
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            //int i = 1/0;
            return 100;
        });
        //future.join();
        System.out.println(completableFuture.get());

        CompletableFuture<String> completableFuture1 = new CompletableFuture<>();
        // 1：get()：一直阻塞，不会完成。
        //completableFuture1.get();
        // 2：complete()：手动完成
        completableFuture1.complete("Future's Result");

        /**
         * 异步运行api:
         * static CompletableFuture<Void>  runAsync(Runnable runnable)
         * static CompletableFuture<Void>  runAsync(Runnable runnable, Executor executor)
         * static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
         * static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor)
         */
        /**
         * Void是 void 的包装类
         * 3：runAsync()：异步的运行一个后台任务，不接受返回值。
         */
        CompletableFuture<Void> completableFuture2 = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("I'll run in a separate thread than the main thread.");
        });
        completableFuture2.get();

        // 4：supplyAsync()：异步的运行一个后台任务，接受返回值。
        CompletableFuture<String> completableFuture3 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Result of the asynchronous computation";
        });
        System.out.println(completableFuture3.get());

        // 5：thenApply()：得到回调值，当future运行结束时执行。
        CompletableFuture<String> completableFuture4 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Rajeev";
        });
        CompletableFuture<String> completableFuture5 = completableFuture4.thenApply(name -> {
            return "hello " + name;
        });
        completableFuture5.get();

        // 5：.thenApply().thenApply().thenApply()：连续给入回调。
        CompletableFuture<String> completableFuture6 = CompletableFuture.supplyAsync(() -> {
            return "zhuchen";
        }).thenApply(name -> {
            return "hello " + name;
        }).thenApply(juzi -> juzi + ", Welcome to the CalliCoder Blog");
        completableFuture6.get();

        // 7：thenAccept() 和 thenRun()：不得到回调值。
        CompletableFuture<Void> completableFuture7 = CompletableFuture.supplyAsync(() -> {
            return "";
        }).thenAccept(product -> {
            System.out.println("Got product detail from remote service 3");
        });

        CompletableFuture.supplyAsync(() -> {
           return  "xxx";
        }).thenRun(() -> {
            // Computation Finished.
        });

        CompletableFuture.supplyAsync(() -> {
            return "Some Result";
        }).thenApply/*同步回调，执行线程在main中*/(result -> {
            // main线程中
            System.out.println(Thread.currentThread().getName());
            return "Processed Result";
        });

        //
        CompletableFuture.supplyAsync(() -> {
            return "Some Result";
        }).thenApplyAsync/*异步回调，执行线程在ForkJoinPool.commonPool-worker-1中*/(result -> {
            // main线程中
            System.out.println(Thread.currentThread().getName());
            return "Processed Result";
        });

        // 使用 exceptionally() 回调处理异常
        Integer age = -1;
        CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
            if(age < 0) {
                throw new IllegalArgumentException("Age can not be negative");
            }
            if(age > 18) {
                return "Adult";
            } else {
                return "Child";
            }
        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return "Unknown!";
        });
        System.out.println("Maturity : " + maturityFuture.get());

        // 使用 handle() 方法处理异常
        CompletableFuture<String> completableFuture8 = CompletableFuture.supplyAsync(() -> {
            if(age < 0) {
                throw new IllegalArgumentException("Age can not be negative");
            }
            if(age > 18) {
                return "Adult";
            } else {
                return "Child";
            }
        }).handle((res, ex) -> {
            if(ex != null) {
                System.out.println("Oops! We have an exception - " + ex.getMessage());
                return "Unknown!";
            }
            return res;
        });

        System.out.println("Maturity : " + completableFuture8.get());

    }

}
