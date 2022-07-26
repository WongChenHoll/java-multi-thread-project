package com.jason.jcip.chapter_01;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * @author WongChenHoll
 * @description
 * @date 2022-7-26 星期二 17:09
 **/
@ThreadSafe
public class SafeSequence {
    @GuardedBy("this")
    private int value = 0;

    // 添加synchronized使其成为一个同步方法
    public synchronized int getValue() {
        return value++;
    }
}
