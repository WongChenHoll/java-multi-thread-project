package com.jason.thread.base;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 使用Thread类创建一个线程，通过类中的start()方式执行。
 * 此形式是将任务和线程合在一起创建。
 *
 * @author WangChenHol
 * @date 2022-2-9 14:37
 **/
public class TestThread {
    private static final Logger logger = LoggerFactory.getLogger(TestThread.class);

    public static void main(String[] args) {

        // 使用Thread类创建一个线程
        new Thread() {
            @Override
            public void run() {
                logger.info("t0 当前线程名：{}", Thread.currentThread().getName());
            }
        }.start();

        // 使用Thread类创建一个线程，使用lambda形式
        Thread t1 = new Thread(() -> logger.info("t1 当前线程名：{}", Thread.currentThread().getName()));
        t1.start();

        // 使用Thread类创建一个线程，线程名：thread线程2
        Thread t2 = new Thread("thread线程2") {
            @Override
            public void run() {
                logger.info("t2 当前线程名：{}", Thread.currentThread().getName());
            }
        };
        t2.start();

        MyThread myThread = new MyThread(10);
        myThread.start();

    }
}

/**
 * 创建一个多线程任务，继承Thread类
 */
class MyThread extends Thread {
    private int num;

    public MyThread(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        System.out.println("线程编号：" + num);
    }
}