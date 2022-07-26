package com.jason.jcip.chapter_01;

import net.jcip.annotations.NotThreadSafe;

/**
 * 非线程安全的数值序列生成器
 * <pre>
 *     该类在单线程中执行时，能够正确的工作，并得到预期中的结果。
 *     但是在多线程中执行将导致不可预料的结果，可能会得到相同的值。
 * </pre>
 * 注解：@NotThreadSafe只是说明此类是非线程安全的类，没有其他作用。
 *
 * @author WongChenHoll
 * @description
 * @date 2022-7-26 星期二 15:57
 **/
@NotThreadSafe
public class UnsafeSequence {
    private int value = 0;

    public int getValue() {
        return value++;
    }
}
