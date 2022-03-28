package com.jason.thread.pool.customThreadPools;

import java.util.List;

/**
 * 自定义线程池——线程类。
 * threadName：保存线程名
 * runnables：保存所有任务
 *
 * @author WongChenHoll
 * @date 2022-2-28 16:24
 **/
public class MyThread extends Thread {

    private String threadName; // 线程名
    private final List<Runnable> runnables;

    public MyThread(String threadName, List<Runnable> runnables) {
        super(threadName);
        this.runnables = runnables;
    }

    @Override
    public void run() {
        while (runnables.size() > 0) {
            Runnable runnable = runnables.remove(0);
            runnable.run();
        }
    }
}
