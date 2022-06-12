package com.jason.jcip.chapter_03;

/**
 * 同步的重要特征之一：内存的可见性。
 * 本类测试的是在没有同步的情况下共享变量。
 * <pre>
 *     本测试类中main方法的结果可能会有多种：
 *     1.可能会永远持续循环下去。
 *     2.可能会输出0.
 *     3.输出42.
 * </pre>
 *
 * @author WongChenHoll
 * @date 2022-6-11 星期六 11:12
 **/
public class NoVisibility {

    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                System.out.println(number);
            }
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }
}
