package com.jason.thread.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 测试多线程的各个状态
 *
 * @author WongChenHoll
 * @date 2022-2-21 17:28
 **/
public class TestThreadState {

    private static final Logger logger = LoggerFactory.getLogger(TestThreadState.class);

    public static void main(String[] args) throws InterruptedException {

//        test01();
//        test02();
        test03();
    }


    /**
     * 测试线程的 NEW、RUNNABLE、TERMINATED状态
     *
     * @throws InterruptedException 异常
     */
    private static void test01() throws InterruptedException {
        SimpleThread ts = new SimpleThread();
        logger.info("执行start()方法之前：线程 [{}] 的状态：{}", ts.getName(), ts.getState()); // NEW
        ts.start();
        // 需要注意的是此时线程并没有执行完成，依次处于RUNNABLE状态
        logger.info("执行start()方法之后：线程 [{}] 的状态：{}", ts.getName(), ts.getState()); // RUNNABLE

        // 等待1秒后，该线程的任务已经执行完成（因为该线程的任务比较简单，很快就能执行完成），所以此时线程的状态是：TERMINATED
        Thread.sleep(1000);
        logger.info("执行sleep()方法之后：线程 [{}] 的状态：{}", ts.getName(), ts.getState()); // TERMINATED
    }

    /**
     * 测试线程的 NEW、RUNNABLE、TIMED_WAITING、TERMINATED状态
     *
     * @throws InterruptedException 异常
     */
    private static void test02() throws InterruptedException {
        SleepThread cs = new SleepThread();
        logger.info("执行start()方法之前：线程 [{}] 的状态：{}", cs.getName(), cs.getState()); // NEW
        cs.start();
        // 需要注意的是此时线程并没有执行完成，依次处于RUNNABLE状态
        logger.info("执行start()方法之后：线程 [{}] 的状态：{}", cs.getName(), cs.getState()); // RUNNABLE

        // 等待1秒后，该线程的任务还没有完成，而这时我们调用了Thread.sleep()方法，所以会使线程进入TIMED_WAITING状态。
        Thread.sleep(1000);
        logger.info("执行sleep()方法之后：线程 [{}] 的状态：{}", cs.getName(), cs.getState()); // TIMED_WAITING

        // 又等待了3秒，此时线程已经执行完成（该线程总共耗时大约3秒），所以此时线程的状态是：TERMINATED
        Thread.sleep(3000);
        logger.info("执行sleep()方法之后：线程 [{}] 的状态：{}", cs.getName(), cs.getState()); // TERMINATED
    }

    /**
     * 测试两个耗时都是5秒的线程同时使用同一把锁，在执行过程中会出现一个线程的状态是：RUNNABLE，另一个线程的状态是：BLOCKED的情况。
     *
     * @throws InterruptedException 异常
     */
    private static void test03() throws InterruptedException {
        Object objectBlock = new Object(); // Object类型的锁对象

        // 创建两个线程，并且同时使用一把锁
        BlockThreadA threadA = new BlockThreadA("A", objectBlock);
        BlockThreadB threadB = new BlockThreadB("B", objectBlock);
        // 此时线程刚创建，因此两个线程的状态都是：NEW
        logger.info("=== {}-线程的状态：{}", threadA.getName(), threadA.getState());
        logger.info("=== {}-线程的状态：{}", threadB.getName(), threadB.getState());

        // 启动两个线程
        threadA.start();
        threadB.start();
        // 线程启动后，线程的状态是：RUNNABLE
        logger.info("### {}-线程的状态：{}", threadA.getName(), threadA.getState());
        logger.info("### {}-线程的状态：{}", threadB.getName(), threadB.getState());

        // 设置3秒睡眠，因为两个线程执行任务的耗时都是5秒，因此此时两个线程都还没执行完任务
        Thread.sleep(3000);
        // 此时线程的状态是先拿到锁的线程状态是：RUNNABLE，另一个线程的状态是：BLOCKED
        logger.info("￥￥￥ {}-线程的状态：{}", threadA.getName(), threadA.getState());
        logger.info("￥￥￥ {}-线程的状态：{}", threadB.getName(), threadB.getState());


        // 在设置睡眠3秒，此时一个线程执行完成，另一个线程正在执行，
        Thread.sleep(3000);
        // 一个状态为：TERMINATED，另一个状态为：RUNNABLE
        logger.info("%%% {}-线程的状态：{}", threadA.getName(), threadA.getState());
        logger.info("%%% {}-线程的状态：{}", threadB.getName(), threadB.getState());

        // 在设置睡眠5秒，此时两个线程都已经执行完毕
        Thread.sleep(5000);
        // 两个线程的状态都是：TERMINATED
        logger.info("@@@ {}-线程的状态：{}", threadA.getName(), threadA.getState());
        logger.info("@@@ {}-线程的状态：{}", threadB.getName(), threadB.getState());
    }
}
