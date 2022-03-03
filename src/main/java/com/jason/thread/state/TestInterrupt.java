package com.jason.thread.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 测试 interrupt() 方法的使用.
 * <pre>
 *     通过本类中的4个测试方法得知：
 *
 *     1.当我们创建的线程或者任务中的run()中使用了Thread.sleep()方法时。
 *      在执行线程未完成时，无论是使用Thread.sleep()还是TimeUnit.SECONDS.sleep()使线程睡眠，
 *      然后再使用interrupt()中断睡眠时，线程会报异常：java.lang.InterruptedException: sleep interrupted。
 *
 *     2.当我们创建的线程或者任务中的run()中不使用了Thread.sleep()方法时，而是通过while(){}使任务执行一段时间，
 *      在执行线程未完成时，无论是使用Thread.sleep()还是TimeUnit.SECONDS.sleep()使线程睡眠，
 *      然后再使用interrupt()中断睡眠时,线程的状态始终是：RUNNABLE，且不会出异常。
 * </pre>
 *
 * @author WongChenHoll
 * @date 2022-3-1 17:06
 **/
public class TestInterrupt {

    private static final Logger logger = LoggerFactory.getLogger(TestInterrupt.class);

    public static void main(String[] args) throws InterruptedException {

//        testTheThreadHasSleepMethod();
//        testTheThreadWithoutSleepMethod();
        testTheRunnableHasSleepMethod();
//        testTheRunnableWithoutSleepMethod();
    }


    /**
     * 被测试的线程的 run() 方法中使用了Thread.sleep(5000) 睡眠5秒。
     * <pre>
     * 在线程执行过程中时：<br/>
     *   1.当使用Thread.sleep(2000) 或者TimeUnit.SECONDS.sleep(2)时，
     *          此时线程还未执行完成，线程状态是：TIMED_WAITING，在使用 interrupt() 中断线程睡眠时，
     *          会报异常：java.lang.InterruptedException: sleep interrupted。<br/>
     *   2.当使用Thread.sleep(6000) 或者TimeUnit.SECONDS.sleep(6) 时，
     *          此时线程已经执行完成，线程状态是：TERMINATED，在使用 interrupt() 中断线程睡眠时，
     *          因为线程已经执行完了，所以不会有影响。
     * </pre>
     *
     * @throws InterruptedException 打断睡眠的线程时 异常。
     */
    private static void testTheThreadHasSleepMethod() throws InterruptedException {
        logger.info("执行方法：testTheThreadHasSleepMethod()");

        SleepThread t1 = new SleepThread();
        logger.info("刚创建时线程状态：{}", t1.getState());
        t1.start();
        logger.info("执行 start()方法之后：{}", t1.getState());

//        Thread.sleep(6000);
//        logger.info("执行 Thread.sleep()方法之后：{}", t1.getState());

        TimeUnit.SECONDS.sleep(6);
        logger.info("执行 TimeUnit.sleep()方法之后：{}", t1.getState());

        t1.interrupt();
        logger.info("执行 interrupt()方法之后：{}", t1.getState());
    }

    /**
     * 被测试的线程的 run() 方法中不使用了Thread.sleep() 睡眠，而是while(){}循环5秒。
     * <pre>
     * 在线程执行过程中时：<br/>
     *   1.当使用Thread.sleep(2000)或者TimeUnit.SECONDS.sleep(2)时，
     *          此时线程还未执行完成，线程状态是：RUNNABLE，在使用 interrupt() 中断线程睡眠时，线程状态还是：RUNNABLE。
     *          不会报异常。<br/>
     *   2.当使用Thread.sleep(6000)或者TimeUnit.SECONDS.sleep(6)时，
     *          此时线程已经执行完成，线程状态是：TERMINATED，在使用 interrupt() 中断线程睡眠时，线程状态还是：TERMINATED。
     *          因为线程已经执行完了，所以不会有影响。
     * </pre>
     *
     * @throws InterruptedException 打断睡眠的线程时 异常。
     */
    private static void testTheThreadWithoutSleepMethod() throws InterruptedException {
        logger.info("执行方法：testTheThreadWithoutSleepMethod()");

        ComplexThread threadState = new ComplexThread();
        logger.info("刚创建时线程状态：{}", threadState.getState());
        threadState.start();
        logger.info("执行 start()方法之后：{}", threadState.getState());

        Thread.sleep(6000);
        logger.info("执行 Thread.sleep()方法之后：{}", threadState.getState());

//        TimeUnit.SECONDS.sleep(6);
//        logger.info("执行 TimeUnit.sleep()方法之后：{}", threadState.getState());

        threadState.interrupt();
        logger.info("执行 interrupt()方法之后：{}", threadState.getState());
    }

    /**
     * 使用一个实现了Runnable接口的类创建一个任务，再通过new Thread(runnable)创建一个线程。该任务中的run()方法中使用Thread.sleep()睡眠5秒。
     * <pre>
     * 在线程执行过程中时：<br/>
     *   1.当使用Thread.sleep(2000)或者TimeUnit.SECONDS.sleep(2)时，
     *          此时线程还未执行完成，线程状态是：TIMED_WAITING，在使用 interrupt() 中断线程睡眠时，线程状态还是：TIMED_WAITING。
     *          同时会报异常：java.lang.InterruptedException: sleep interrupted。<br/>
     *   2.当使用Thread.sleep(6000)或者TimeUnit.SECONDS.sleep(6)时，
     *          此时线程已经执行完成，线程状态是：TERMINATED，在使用 interrupt() 中断线程睡眠时，线程状态还是：TERMINATED。
     *          因为线程已经执行完了，所以不会有影响。
     * </pre>
     *
     * @throws InterruptedException 打断睡眠的线程时 异常。
     */
    private static void testTheRunnableHasSleepMethod() throws InterruptedException {
        logger.info("执行方法：testTheRunnableHasSleepMethod()");

        SleepRunnable runnable = new SleepRunnable();
        Thread thread = new Thread(runnable);
        logger.info("刚创建时线程状态：{}", thread.getState());
        thread.start();
        logger.info("执行 start()方法之后：{}", thread.getState());

//        Thread.sleep(2000);
        Thread.sleep(6000);
        logger.info("执行 Thread.sleep()方法之后：{}", thread.getState());

//        TimeUnit.SECONDS.sleep(2);
//        TimeUnit.SECONDS.sleep(6);
//        logger.info("执行 TimeUnit.sleep()方法之后：{}", thread.getState());

        thread.interrupt();
        logger.info("执行 interrupt()方法之后：{}", thread.getState());
    }

    /**
     * 使用一个实现了Runnable接口的类创建一个任务，再通过new Thread(runnable)创建一个线程。该任务中的run()方法中使用while(){}循环5秒。
     * <pre>
     * 在线程执行过程中时：<br/>
     *   1.当使用Thread.sleep(2000)或者TimeUnit.SECONDS.sleep(2)时，
     *          此时线程还未执行完成，线程状态是：RUNNABLE，在使用 interrupt() 中断线程睡眠时，线程状态还是：RUNNABLE。
     *          不会报异常。<br/>
     *   2.当使用Thread.sleep(6000)或者TimeUnit.SECONDS.sleep(6)时，
     *          此时线程已经执行完成，线程状态是：TERMINATED，在使用 interrupt() 中断线程睡眠时，线程状态还是：TERMINATED。
     *          因为线程已经执行完了，所以不会有影响。
     * </pre>
     *
     * @throws InterruptedException 打断睡眠的线程时 异常。
     */
    private static void testTheRunnableWithoutSleepMethod() throws InterruptedException {
        logger.info("执行方法：testTheRunnableWithoutSleepMethod()");

        ComplexRunnable runnable = new ComplexRunnable();
        Thread thread = new Thread(runnable);
        logger.info("刚创建时线程状态：{}", thread.getState());
        thread.start();
        logger.info("执行 start()方法之后：{}", thread.getState());

//        Thread.sleep(2000);
//        Thread.sleep(6000);
//        logger.info("执行 Thread.sleep()方法之后：{}", thread.getState());

//        TimeUnit.SECONDS.sleep(2);
        TimeUnit.SECONDS.sleep(6);
        logger.info("执行 TimeUnit.sleep()方法之后：{}", thread.getState());

        thread.interrupt();
        logger.info("执行 interrupt()方法之后：{}", thread.getState());
    }

}