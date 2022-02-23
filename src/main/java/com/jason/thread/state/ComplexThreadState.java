package com.jason.thread.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 该多线程用来测试线程在各个阶段的线程状态（线程任务需要一定的时间，这里设置为3秒）。
 *
 * @author WongChenHoll
 * @date 2022-2-21 17:22
 **/
public class ComplexThreadState extends Thread {
    private String threadName; //线程名
    private int threadNum; // 线程编号


    public ComplexThreadState() {
        super("默认线程名");
    }

    public ComplexThreadState(String threadName) {
        super(threadName);
    }

    private static final Logger logger = LoggerFactory.getLogger(ComplexThreadState.class);

    @Override
    public void run() {
        logger.info("开始执行run()方法时的线程状态：{}", Thread.currentThread().getState());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            logger.error("任务执行异常：", e);
        }
        logger.info("========== 任务执行完成 ===========");
    }
}