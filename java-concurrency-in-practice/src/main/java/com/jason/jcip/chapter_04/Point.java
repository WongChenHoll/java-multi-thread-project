package com.jason.jcip.chapter_04;

import net.jcip.annotations.Immutable;

/**
 * 不可变的坐标类。（与{@link java.awt.Point} 类似）
 *
 * @author WongChenHoll
 * @version 2.0
 * @date 2022-6-14 星期二 17:25
 **/
@Immutable
public class Point {

    public final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
