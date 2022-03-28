package com.jason.thread.pool.customThreadPools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义线程池——任务类
 * 包括线程编号，任务执行时间1秒
 *
 * @author WongChenHoll
 * @date 2022-2-28 16:20
 **/
public class MyRunnable implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(MyRunnable.class);

    private final int taskNum; // 任务编号

    public MyRunnable(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void run() {
        logger.info("线程[{}]开始执行任务，任务编号：[{}]", Thread.currentThread().getName(), taskNum);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            logger.error("执行任务异常：", e);
        }
        logger.info("线程[{}]完成任务！，任务编号：[{}]", Thread.currentThread().getName(), taskNum);
    }
}
