package com.jason.thread.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author WongChenHoll
 * @date 2022-3-3 16:26
 **/
public class ComplexRunnable implements Runnable{

    private static final Logger logger= LoggerFactory.getLogger(ComplexRunnable.class);

    @Override
    public void run() {
        logger.info("开始执行run()方法时的线程状态：{}", Thread.currentThread().getState());
        long s = System.currentTimeMillis();
        while (System.currentTimeMillis() - s < 5000) {
            // do something
        }
        logger.info("========== ComplexRunnable 任务执行完成 ===========");
    }
}
