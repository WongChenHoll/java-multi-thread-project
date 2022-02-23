package com.jason.thread.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

/**
 * @author WongChenHoll
 * @date 2022-2-23 16:26
 **/
public class BlockThreadA extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(BlockThreadA.class);

    private final Object block;

    public BlockThreadA(String name, Object block) {
        super(name);
        this.block = block;
    }

    @Override
    public void run() {
        // 添加锁，保证线程安全
        synchronized (block) {
            logger.info("{}-线程开始执行，执行时间：{}", getName(), LocalDateTime.now().toString());
            long start = System.currentTimeMillis();
            // 设置任务执行耗时5秒
            while (System.currentTimeMillis() - start < 5000) {
                // do something
            }
            logger.info("{}-线程执行完成！，完成时间：{}", getName(), LocalDateTime.now().toString());
        }
    }
}
