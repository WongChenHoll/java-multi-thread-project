package com.jason.thread.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 任务类，包括任务编号。每个任务需要耗时5秒
 *
 * @author WongChenHoll
 * @date 2022-2-25 17:38
 **/
public class ComplexRunnable implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(ComplexRunnable.class);

    private final int taskNum; // 任务编号

    public ComplexRunnable(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void run() {
        long s = System.currentTimeMillis();
        while (System.currentTimeMillis() - s < 5000) {
            // do something need 5 milliseconds
        }
        logger.info("[{}]-耗时5秒的线程执行了第 {} 个任务", Thread.currentThread().getName(), taskNum);
    }
}
