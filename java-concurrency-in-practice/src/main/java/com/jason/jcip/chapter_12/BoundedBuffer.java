package com.jason.jcip.chapter_12;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.Semaphore;


/**
 * 该类用于并发程序的测试对象.
 *
 * <pre>
 *     程序清单 12-2，P206。
 *     普通测试参考单元测试：{@link com.jason.jcip.chapter_12.BoundedBufferTest}
 * </pre>
 *
 * @author WongChenHoll
 * @description 基于信号量的有界缓存
 * @date 2022-7-5 星期二 16:16
 **/
@ThreadSafe
public class BoundedBuffer<E> {
    private final Semaphore availableItems, avaliableSpaces;
    @GuardedBy("this")
    private final E[] items;
    @GuardedBy("this")
    private int putPosition = 0, takePosition = 0;

    public BoundedBuffer(int capacity) {
        availableItems = new Semaphore(0);
        avaliableSpaces = new Semaphore(capacity);
        items = (E[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return availableItems.availablePermits() == 0;
    }

    public boolean isFull() {
        return avaliableSpaces.availablePermits() == 0;
    }

    public void put(E e) throws InterruptedException {
        avaliableSpaces.acquire();
        doInsert(e);
        availableItems.release();
    }

    public E take() throws InterruptedException {
        availableItems.acquire();
        E item = doExtract();
        avaliableSpaces.release();
        return item;
    }

    private synchronized void doInsert(E e) {
        int i = putPosition;
        items[i] = e;
        putPosition = (++i == items.length) ? 0 : i;
    }

    private synchronized E doExtract() {
        int i = takePosition;
        E e = items[i];
        items[i] = null;
        takePosition = (++i == items.length) ? 0 : i;
        return e;
    }
}
