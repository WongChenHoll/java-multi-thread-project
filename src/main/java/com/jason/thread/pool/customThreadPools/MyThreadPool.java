package com.jason.thread.pool.customThreadPools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 自定义线程池——线程池
 *
 * @author WongChenHoll
 * @date 2022-2-28 16:30
 **/
public class MyThreadPool {

    private static final Logger logger = LoggerFactory.getLogger(MyThreadPool.class);

    private List<Runnable> runnables = Collections.synchronizedList(new LinkedList<>());

    private int currentThreadNum; // 当前线程数量
    private int corePoolSize; // 核心线程数量
    private int maxThreadSize; // 最大线程数量
    private int threadQueueSize; // 线程队列容量

    public MyThreadPool(int corePoolSize, int maxThreadSize, int threadQueueSize) {
        this.corePoolSize = corePoolSize;
        this.maxThreadSize = maxThreadSize;
        this.threadQueueSize = threadQueueSize;
    }

    /**
     * 提交任务
     *
     * @param r 任务
     */
    public void submit(Runnable r) {
        if (runnables.size() >= threadQueueSize) {
            logger.error("线程队列已达到最大值，该线程[{}]被遗弃！", r.toString());
        } else {
            runnables.add(r);
            executeRunnable(r);
        }
    }

    private void executeRunnable(Runnable r) {
        if (currentThreadNum < corePoolSize) {
            new MyThread("核心线程" + currentThreadNum, runnables).start();
            currentThreadNum++;
        } else if (currentThreadNum < maxThreadSize) {
            new MyThread("非核心线程" + currentThreadNum, runnables).start();
            currentThreadNum++;
        } else {
            logger.info("任务：[{}]被缓存了", r.toString());
        }
    }

}
