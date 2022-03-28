package com.jason.thread.pool.executors;

import com.jason.thread.pool.ComplexRunnable;
import com.jason.thread.pool.SimpleRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 测试使用Executors.newFixedThreadPool(nThreads)方法创建一个可重用固定线程数的线程池 。
 * <br>
 * 1. Executors.newFixedThreadPool(nThreads)
 * 比如我们设置线程数量是5，则该线程池中始终都会有5个线程存活，如果有一个任务进来，这时5个线程都在执行任务，则新进来的任务则会进入队列中，直到有线程空闲你下来。
 * 如果再线程执行任务的过程中发生异常而终止，则会新建一个线程，使线程池中的线程数量保持不变。
 * 2.Executors.newFixedThreadPool(nThreads,ThreadFactory)
 * 创建一个可重用固定线程数的线程池且线程池中的所有线程都使用ThreadFactory来创建。
 * <br/>
 *
 * @author WongChenHoll
 * @date 2022-2-25 16:12
 **/
public class NewFixedThreadPool {
    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    /**
     * 说明：在此方法中,使用的是 ComplexNewFixedThreadPoolRunnable 任务，此任务执行完成需要5秒。
     * 我们设置的线程池固定数量是5，共提交10个任务，因此在执行过程会首先一起执行前5个任务。
     * 等有任务空闲时再去执行接下来的任务。
     * 在此例子中，相当于每个线程执行两个任务。
     */
    private static void test1() {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            pool.submit(new ComplexRunnable(i));
        }
        pool.shutdown();
    }

    /**
     * 在此方法中使用的是 SimpleNewFixedThreadPoolRunnable 任务，每个任务耗时很短。
     * 在此例子中相当于刚开始每个线程执行一个任务，然后其中一个线程会一直执行剩下的5个任务。
     */
    private static void test2() {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            pool.submit(new SimpleRunnable(i));
        }
        pool.shutdown();
    }

    /**
     * 使用ThreadFactory线程工厂自定义线程。
     */
    private static void test3() {
        ExecutorService pool = Executors.newFixedThreadPool(5, new ThreadFactory() {

            // 使用自定义的线程
            private int taskNum; // 任务编号

            @Override
            public Thread newThread(Runnable r) {
                String name = "自定义线程名-" + taskNum++;
                return new Thread(r, name);
            }
        });
        for (int i = 0; i < 10; i++) {
            pool.submit(new SimpleRunnable(i));
        }
        pool.shutdown();
    }

}
