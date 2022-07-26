package com.jason.jcip.utils;

/**
 * 随机数生成器
 *
 * @author WongChenHoll
 * @description
 * @date 2022-7-6 星期三 16:00
 **/
public class RngUtil {

    /**
     * 适合在测试中使用的随机数生成器
     */
    public static int xorShift(int y) {
        y ^= (y << 6);
        y ^= (y >>> 21);
        y ^= (y << 7);
        return y;
    }
}
