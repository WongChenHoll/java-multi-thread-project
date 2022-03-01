package com.jason.thread.pool.money;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 测试存/取款的功能
 *
 * @author WongChenHoll
 * @date 2022-3-1 11:06
 **/
public class TestWithDrawMoney {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool(new ThreadFactory() {
            private int num = 1;

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "ATM-" + num++);
            }
        });
        pool.submit(new WithdrawMoney("张三", 800.00)); // 存钱800
        pool.submit(new WithdrawMoney("李四", 1000.00)); // 存钱1000
        pool.submit(new WithdrawMoney("王五", -900.00)); // 取钱900
        pool.shutdown();
    }
}
