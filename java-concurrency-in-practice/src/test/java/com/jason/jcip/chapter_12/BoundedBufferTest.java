package com.jason.jcip.chapter_12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 对类{@link com.jason.jcip.chapter_12.BoundedBuffer}进行并发的测试
 *
 * @author WongChenHoll
 * @description
 * @date 2022-7-5 星期二 16:34
 **/
public class BoundedBufferTest {


    private static final long LOCKUP_DETECT_TIMEOUT = 10;

    /**
     * 测试缓存是否为空。
     * 简单的串行测试
     */
    @Test
    public void testIfEmpty() {
        BoundedBuffer<Integer> boundedBuffer = new BoundedBuffer<>(10);
        Assertions.assertTrue(boundedBuffer.isEmpty());
        Assertions.assertFalse(boundedBuffer.isFull());
    }

    /**
     * 测试缓存是否满了。
     * 简单的串行测试
     *
     * @throws InterruptedException 中断异常
     */
    @Test
    public void testIsFull() throws InterruptedException {
        BoundedBuffer<Integer> boundedBuffer = new BoundedBuffer<>(10);
        for (int i = 0; i < 10; i++) {
            boundedBuffer.put(i);
        }
        Assertions.assertFalse(boundedBuffer.isEmpty());
        Assertions.assertTrue(boundedBuffer.isFull());
    }

    /**
     * 测试阻塞操作的方法。
     * <pre>
     *     说明：该方法会创建一个线程，然后该线程会从空缓存中获取一个元素（take()方法），
     *     如果take方法成功，则表示测试失败，如果该线程正确的在take()方法中阻塞，那么将抛出{@link InterruptedException}异常，而捕获到这个异常的catch块将把这个异常视为测试成功，
     *     并让线程退出。
     * </pre>
     */
    @Test
    public void testTakeBlocksWhenEmpty() {
        BoundedBuffer<Integer> bounded = new BoundedBuffer<>(10);
        Thread taker = new Thread(() -> {
            try {
                bounded.take();
                fail();
            } catch (InterruptedException e) {
                System.out.println("中断异常信息：" + e);
            }
        });
        try {
            taker.start();
            Thread.sleep(LOCKUP_DETECT_TIMEOUT);
            taker.interrupt();
            taker.join(LOCKUP_DETECT_TIMEOUT);
            Assertions.assertFalse(taker.isAlive());
        } catch (Exception e) {
            fail(e);
        }
    }

    private void fail() {
        System.out.println("执行失败！");
    }

    private void fail(Exception e) {
        System.out.println("执行失败！" + e.getMessage());
    }
}
