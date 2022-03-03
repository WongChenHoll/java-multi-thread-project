package com.jason.thread.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 该多线程用来测试线程在各个阶段的线程状态（线程任务需要一定的时间，这里使用Thread.sleep()设置为5秒）。
 *
 * @author WongChenHoll
 * @date 2022-2-21 17:22
 **/
public class SleepThread extends Thread {
    private String threadName; //线程名

    public SleepThread() {
        super("默认线程名");
    }

    public SleepThread(String threadName) {
        super(threadName);
    }

    private static final Logger logger = LoggerFactory.getLogger(SleepThread.class);

    @Override
    public void run() {
        logger.info("开始执行run()方法时的线程状态：{}", Thread.currentThread().getState());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            logger.error("任务执行异常：", e);
        }
        logger.info("========== SleepThread 任务执行完成 ===========");
    }
}