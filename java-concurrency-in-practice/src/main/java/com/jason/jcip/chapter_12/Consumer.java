package com.jason.jcip.chapter_12;

/**
 * 在{@link PutTakeTest}中使用的消费者。
 *
 * @author WongChenHoll
 * @description 程序清单 12-6，P211
 * @date 2022-7-7 星期四 16:03
 **/
public class Consumer implements Runnable {
    private final PutTakeTest putTakeTest;

    public Consumer(PutTakeTest putTakeTest) {
        this.putTakeTest = putTakeTest;
    }

    @Override
    public void run() {
        try {
            putTakeTest.barrier.await();
            int sum = 0;
            for (int i = putTakeTest.nTrials; i > 0; --i) {
                sum += putTakeTest.boundedBuffer.take();
            }
            putTakeTest.takeSum.getAndAdd(sum);
            putTakeTest.barrier.await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
