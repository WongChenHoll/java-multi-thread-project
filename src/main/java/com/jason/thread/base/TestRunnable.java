package com.jason.thread.base;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 使用Runnable接口创建一个任务，再通过Thread类创建一个线程。
 * 此方式更加灵活
 *
 * @author WangChenHol
 * @date 2022-2-9 16:05
 **/
public class TestRunnable {
    private static final Logger logger = LoggerFactory.getLogger(TestRunnable.class);

    public static void main(String[] args) {

        // 使用Runnable接口创建一个任务，并通过Thread创建一个线程执行这个任务
        Runnable r0 = new Runnable() {
            @Override
            public void run() {
                logger.info("r0 当前线程名：{}", Thread.currentThread().getName());
            }
        };
        new Thread(r0).start();

        // 使用Runnable接口创建一个任务，并通过Thread创建一个线程执行这个任务，使用lambda表达式
        Runnable r1 = () -> logger.info("r1 当前线程名：{}", Thread.currentThread().getName());
        new Thread(r1).start();

        // 使用Runnable接口创建一个任务，并通过Thread创建一个线程执行这个任务，线程名：Runnable线程6
        Runnable r2 = () -> logger.info("r2 当前线程名：{}", Thread.currentThread().getName());
        Thread t3 = new Thread(r2);
        t3.setName("Runnable线程6");
        t3.start();

        // 使用Runnable接口创建一个任务，并通过Thread创建一个线程执行这个任务，线程名：Runnable线程7
        Runnable r3 = () -> logger.info("r3 当前线程名：{}", Thread.currentThread().getName());
        new Thread(r3, "Runnable线程7").start();

        // 使用Runnable接口创建一个任务，并通过Thread创建一个线程执行这个任务，线程名：Runnable线程8
        new Thread(() -> logger.info("r4 当前线程名：{}", Thread.currentThread().getName()), "Runnable线程8").start();


        MyRunnable myRunnable = new MyRunnable(11);
        Thread thread = new Thread(myRunnable);
        thread.start();
    }
}

class MyRunnable implements Runnable {

    private int num;

    public MyRunnable(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        System.out.println("线程编号：" + num);
    }
}
