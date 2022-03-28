package com.jason.thread.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 使用 TimeUnit.SECONDS.sleep() 使线程睡眠5秒。
 *
 * @author WongChenHoll
 * @date 2022-3-3 17:36
 **/
public class TimeUtilSleepThread extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(TimeUtilSleepThread.class);

    @Override
    public void run() {
        logger.info("开始执行run()方法时的线程状态：{}", Thread.currentThread().getState());
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            logger.error("任务执行异常：", e);
        }
        logger.info("========== TimeUtilSleepThread 任务执行完成 ===========");
    }
}
