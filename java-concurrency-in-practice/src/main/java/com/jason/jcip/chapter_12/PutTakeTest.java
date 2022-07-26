package com.jason.jcip.chapter_12;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试 {@link BoundedBuffer}的生产者-消费者
 *
 * @author WongChenHoll
 * @description 程序程序清单 12-5，P210
 * @date 2022-7-7 星期四 15:51
 **/
public class PutTakeTest {
    public static final ExecutorService pool = Executors.newCachedThreadPool();
    final AtomicInteger putSum = new AtomicInteger(0);
    final AtomicInteger takeSum = new AtomicInteger(0);
    final CyclicBarrier barrier;
    final BoundedBuffer<Integer> boundedBuffer;
    final int nTrials, nPairs;

    public PutTakeTest(int capacity, int nTrials, int nPairs) {
        this.boundedBuffer = new BoundedBuffer<>(capacity);
        this.nTrials = nTrials;
        this.nPairs = nPairs;
        this.barrier = new CyclicBarrier(nPairs * 2 + 1);
    }

    void test() {
        try {
            for (int i = 0; i < nPairs; i++) {
                pool.execute(new Producer(this));
                pool.execute(new Consumer(this));
            }
            barrier.await();
            barrier.await();
            System.out.println("存：" + putSum.get() + "   取：" + takeSum.get() + "  存取是否一样：" + (putSum.get() == takeSum.get()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new PutTakeTest(10, 10, 10000).test();
        pool.shutdown();
    }


}
