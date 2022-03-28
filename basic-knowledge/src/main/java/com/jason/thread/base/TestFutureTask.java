package com.jason.thread.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 使用Runnable接口创建一个任务，将此任务交给FutureTask，实现其异步执行，然后通过Thread类创建一个线程去执行。
 *
 * @author WangChenHol
 * @date 2022-2-9 16:14
 **/
public class TestFutureTask {

    private static final Logger logger = LoggerFactory.getLogger(TestFutureTask.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 使用Runnable接口创建一个名为：“FutureTask线程1”的任务，将此任务交给FutureTask，实现其异步执行，然后通过Thread类创建一个线程去执行。
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                logger.info("task1 当前线程名：{}", Thread.currentThread().getName());
            }
        };
        FutureTask<Integer> task1 = new FutureTask<>(r1, 100);
        new Thread(task1, "FutureTask线程1").start();
        // 主线程阻塞，同步等待task1执行完毕的结果
        Integer integer = task1.get();
        logger.info("FutureTask线程1 运行结果：{}", integer);


        // 使用Runnable接口创建一个名为：“FutureTask线程2”的任务，将此任务交给FutureTask，实现其异步执行，然后通过Thread类创建一个线程去执行。
        // Runnable接口实例通过lambda表达式实现。
        FutureTask<String> task2 = new FutureTask<>(() -> {
            logger.info("task2 当前线程名：{}", Thread.currentThread().getName());
            return "haha";
        });
        new Thread(task2, "FutureTask线程2").start();
        logger.info("FutureTask线程2 运行结果：{}", task2.get());
    }
}
