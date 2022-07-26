package com.jason.jcip.chapter_12;

import com.jason.jcip.utils.RngUtil;

/**
 * 在{@link PutTakeTest}中使用的生产者。
 *
 * @author WongChenHoll
 * @description 程序清单 12-6，P210
 * @date 2022-7-7 星期四 16:03
 **/
public class Producer implements Runnable {
    private final PutTakeTest putTakeTest;

    public Producer(PutTakeTest putTakeTest) {
        this.putTakeTest = putTakeTest;
    }

    @Override
    public void run() {
        try {
            int seed = (this.hashCode() ^ (int) System.nanoTime());
            int sum = 0;
            putTakeTest.barrier.await();
            for (int i = putTakeTest.nTrials; i > 0; --i) {
                putTakeTest.boundedBuffer.put(seed);
                sum += seed;
                seed = RngUtil.xorShift(seed);
            }
            putTakeTest.putSum.getAndAdd(sum);
            putTakeTest.barrier.await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
