package com.jason.thread.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 任务类，包括任务编号。
 *
 * @author WongChenHoll
 * @date 2022-2-25 17:37
 **/
public class SimpleRunnable implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(SimpleRunnable.class);

    private final int taskNum; // 任务编号

    public SimpleRunnable(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void run() {
        logger.info("[{}]-线程执行了第 {} 个任务，执行时间：{}", Thread.currentThread().getName(), taskNum, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
    }
}
