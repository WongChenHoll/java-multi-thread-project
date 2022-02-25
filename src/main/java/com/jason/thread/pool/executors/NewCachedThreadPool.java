package com.jason.thread.pool.executors;

import com.jason.thread.pool.SimpleRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 测试使用Executors.newCachedThreadPool()方法创建一个可缓存的线程池。
 *
 * @author WongChenHoll
 * @date 2022-2-25 15:12
 **/
public class NewCachedThreadPool {

    public static void main(String[] args) {
        test1();
        test2();
    }

    /**
     * 通过Executors.newCachedThreadPool()方式创建一个线程池。
     * 该方式：创建一个默认的线程池对象,里面的线程可重用,且在第一次使用时才创建。如果没有空闲的线程可用，将会创建一个新的线程。
     * 60秒内未使用的线程将被终止并从缓存中删除。因此，一个长时间保持空闲的池不会消耗任何资源。
     */
    private static void test1() {
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            pool.submit(new SimpleRunnable(i));
        }
        pool.shutdown();
    }

    /**
     * 通过Executors.newCachedThreadPool(ThreadFactory)方式创建一个线程池。
     * 与上面方法不同的是可以自定义线程，而不是去使用默认创建的线程。
     */
    private static void test2() {
        ExecutorService pool = Executors.newCachedThreadPool(new ThreadFactory() {
            // 使用ThreadFactory工厂自定义线程。
            int i = 1;

            @Override
            public Thread newThread(Runnable r) {
                String name = "自定义的线程名-" + i++;
                return new Thread(r, name);
            }
        });

        for (int i = 0; i < 5; i++) {
            pool.submit(new SimpleRunnable(i));
        }
        pool.shutdown();
    }
}

