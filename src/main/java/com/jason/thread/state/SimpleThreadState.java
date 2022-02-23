package com.jason.thread.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 该多线程用来测试线程在各个阶段的线程状态（线程任务比较简单，很快就能执行完成）。
 *
 * @author WongChenHoll
 * @date 2022-2-21 17:22
 **/
public class SimpleThreadState extends Thread {
    private String threadName; //线程名
    private int threadNum; // 线程编号


    public SimpleThreadState() {
        super("默认线程名");
    }

    public SimpleThreadState(String threadName) {
        super(threadName);
    }

    private static final Logger logger = LoggerFactory.getLogger(SimpleThreadState.class);

    @Override
    public void run() {
        logger.info("开始执行run()方法时的线程状态：{}", Thread.currentThread().getState());
        logger.info("========== 任务执行完成 ===========");
    }
}