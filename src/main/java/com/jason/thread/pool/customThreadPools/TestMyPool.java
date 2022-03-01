package com.jason.thread.pool.customThreadPools;

/**
 * 测试自定义的线程池
 *
 * @author WongChenHoll
 * @date 2022-2-28 17:11
 **/
public class TestMyPool {
    public static void main(String[] args) {
        MyThreadPool pool = new MyThreadPool(2, 10, 5);
        for (int i = 0; i < 20; i++) {
            pool.submit(new MyRunnable(i));
        }
    }
}
