package com.nebula.juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * <p>
 * ForkJoinPool：并行计算框架。
 * 1.7提出。
 * </p>
 * @author: zhu.chen
 * @date: 2020/7/24
 * @version: v1.0.0
 */
public class ForkJoinPoolDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Task countTask = new Task(1, 100);
        ForkJoinTask<Integer> result = forkJoinPool.submit(countTask);
        System.out.println(result.get());
        forkJoinPool.shutdown();
    }

    static class Task extends RecursiveTask<Integer> {

        private static final long serialVersionUID = -1124359429852338466L;

        private int start;

        private int end;

        private int mid;

        public Task(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            int sum = 0;
            if (end - start < 6) {
                for (int i = start; i <= end; i++) {
                    sum += i;
                }
                //System.out.println(Thread.currentThread().getName() + " count sum: " + sum);
                //return sum;
            } else {
                mid = (end - start) / 2 + start;
                Task left = new Task(start, mid);
                Task right = new Task(mid + 1, end);
                invokeAll(left, right);
                sum +=  left.join() + right.join();
            }
            return sum;
        }

    }

}
