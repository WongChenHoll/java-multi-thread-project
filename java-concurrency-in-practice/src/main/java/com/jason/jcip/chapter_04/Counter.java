package com.jason.jcip.chapter_04;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * 使用Java监视器模式的线程安全计数器
 *
 * @author WongChenHoll
 * @date 2022-6-14 星期二 16:30
 **/
@ThreadSafe
public class Counter {

    @GuardedBy("this")
    private long value = 0;

    public synchronized long getValue() {
        return value;
    }

    public synchronized long increment() {
        if (value == Long.MAX_VALUE) {
            throw new IllegalStateException("counter overflow");
        }
        return ++value;
    }
}
