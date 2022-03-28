package com.jason.thread.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 该多线程用来测试线程在各个阶段的线程状态（线程任务需要一定的时间，这里设置为3秒）。
 *
 * @author WongChenHoll
 * @date 2022-2-21 17:22
 **/
public class ComplexThread extends Thread {
    private String threadName; //线程名


    public ComplexThread() {
        super("默认线程名");
    }

    public ComplexThread(String threadName) {
        super(threadName);
    }

    private static final Logger logger = LoggerFactory.getLogger(ComplexThread.class);

    @Override
    public void run() {
        logger.info("开始执行run()方法时的线程状态：{}", Thread.currentThread().getState());
        long s = System.currentTimeMillis();
        while (System.currentTimeMillis() - s < 5000) {
            // do something
        }
        logger.info("========== ComplexThread 任务执行完成 ===========");
    }
}