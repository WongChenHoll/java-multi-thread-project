package com.jason.thread.method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 比较run()和start()方法的区别
 *
 * @author WangChenHol
 * @date 2022-2-10 14:37
 **/
public class RunAndStart {

    private static final Logger logger = LoggerFactory.getLogger(RunAndStart.class);

    public static void main(String[] args) {
        testRun();
        testStart();
    }


    /**
     * 调用run()方法，是在主线程中执行了run()方法，并没有启动新的线程。
     * 任务和线程是同步的。
     */
    public static void testRun() {
        logger.info("=== testRun() start ......");
        Thread thread = new Thread("t1") {
            @Override
            public void run() {
                logger.info("线程t1执行...");
            }
        };
        thread.run();
        logger.info("=== testRun() end ......");
    }


    /**
     * 调用start()方法，启动一个新的线程执行任务。
     * 主线程和任务是异步的。
     */
    public static void testStart() {
        logger.info("=== testStart() start ......");
        Thread thread = new Thread("t2") {
            @Override
            public void run() {
                logger.info("线程t2执行...");
            }
        };
        thread.start();
        logger.info("=== testStart() end ......");
    }
}
