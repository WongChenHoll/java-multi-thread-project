package com.jason.thread.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 创建一个任务，实现Runnable接口。任务中使用Thread.sleep()睡眠5秒，表示该任务需要5秒执行完成。
 *
 * @author WongChenHoll
 * @date 2022-3-3 16:17
 **/
public class SleepRunnable implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(SleepRunnable.class);

    @Override
    public void run() {
        logger.info("开始执行run()方法时的线程状态：{}", Thread.currentThread().getState());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            logger.error("任务执行异常：", e);
        }
        logger.info("========== SleepRunnable 任务执行完成 ===========");
    }
}
