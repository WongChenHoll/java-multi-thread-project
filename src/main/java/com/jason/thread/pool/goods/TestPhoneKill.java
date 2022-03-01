package com.jason.thread.pool.goods;

import java.util.concurrent.*;

/**
 * @author WongChenHoll
 * @date 2022-3-1 14:27
 **/
public class TestPhoneKill {
    public static void main(String[] args) {
//        ExecutorService pool = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(15));
        for (int i = 1; i <= 20; i++) {
            executor.submit(new PhoneSecondsKill("用户-" + i));
        }
        executor.shutdown();
    }
}
