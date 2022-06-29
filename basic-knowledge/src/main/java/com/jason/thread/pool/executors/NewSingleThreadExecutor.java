package com.jason.thread.pool.executors;

import com.jason.thread.pool.ComplexRunnable;
import com.jason.thread.pool.SimpleRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 使用Executors.newSingleThreadExecutor()创建一个使用单个 worker 线程的 Executor，以无界队列方式来运行该线程。
 *
 * @author WongChenHoll
 * @date 2022-2-25 17:06
 **/
public class NewSingleThreadExecutor {
    public static void main(String[] args) {
//        test1();
//        test2();
        test3();

    }

    /**
     * 只有一个线程，所以要一个一个任务去执行。
     */
    private static void test1() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            executor.submit(new SimpleRunnable(i));
        }
        executor.shutdown();
    }

    /**
     * 只有一个线程，所以要一个一个任务去执行。每个任务耗时5秒。
     */
    private static void test2() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            executor.submit(new ComplexRunnable(i));
        }
        executor.shutdown();
    }

    /**
     * 只有一个线程，所以要一个一个任务去执行。并且使用的是自定义的线程
     */
    private static void test3() {
        ExecutorService executor = Executors.newSingleThreadExecutor(new MyThreadFactory("牛逼线程池"));
        for (int i = 0; i < 5; i++) {
            executor.submit(new SimpleRunnable(i));
        }
        executor.shutdown();
    }

    static class MyThreadFactory implements ThreadFactory {
        private final String poolName;

        public MyThreadFactory(String poolName) {
            this.poolName = poolName;
        }

        @Override
        public Thread newThread(Runnable r) {
            String name = "我的线程";
            return new Thread(r, poolName + "之☞-" + name);
        }
    }
}
