package com.jason.jcip.chapter_04;

import net.jcip.annotations.NotThreadSafe;

/**
 * 可变的坐标类。（与{@link Point} 类似）
 * <pre>
 *     Mutable
 *      adj. 可变的，易变的；反复无常的，用情不专的
 * </pre>
 * <p>
 *
 * @author WongChenHoll
 * @version 1.0
 * @date 2022-6-14 星期二 16:50
 **/
@NotThreadSafe
public class MutablePoint {

    public int x, y;

    public MutablePoint() {
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint point) {
        this.x = point.x;
        this.y = point.y;
    }
}
