package com.jason.thread.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        String start = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
        long s = System.currentTimeMillis();
        while (System.currentTimeMillis() - s < 5000) {
            // do something need 5 milliseconds
        }
        String end = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
        logger.info("[{}]-耗时5秒的线程执行了第 {} 个任务，执行开始时间：{}，执行结束时间：{}", Thread.currentThread().getName(), taskNum, start, end);
    }
}
