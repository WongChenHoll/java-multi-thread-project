package com.jason.jcip.chapter_01;

/**
 * @author WongChenHoll
 * @description
 * @date 2022-7-26 星期二 16:05
 **/
public class TestClass {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("单线程中使用不安全的序列生成器：");
//        getSequenceUseUnsafeSequenceInSingleThread();
//        System.out.println("\r\n多线程中使用不安全的序列生成器：");
//        getSequenceUseUnsafeSequenceInMultiThread();
        System.out.println("\r\n多线程中使用安全的序列生成器：");
        getSequenceUseSafeSequenceInMultiThread();
    }

    /**
     * 使用 {@link UnsafeSequence}在单线程中生成序列集合
     */
    public static void getSequenceUseUnsafeSequenceInSingleThread() {
        UnsafeSequence sequence = new UnsafeSequence();
        for (int i = 0; i < 20; i++) {
            System.out.print(sequence.getValue() + " ");
        }
    }

    /**
     * 使用 {@link UnsafeSequence}在多线程中生成序列集合.
     * 会生成相同的数字
     */
    public static void getSequenceUseUnsafeSequenceInMultiThread() throws InterruptedException {
        UnsafeSequence unsafeSequence = new UnsafeSequence();
        // 启用8个线程执行，每个线程生成5个序列
        for (int i = 0; i < 15; i++) {
            Thread t1 = new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.print(unsafeSequence.getValue() + " ");
                }
            });
            t1.start();
        }
    }


    /**
     * 使用 {@link SafeSequence}在多线程中生成序列集合。
     * 不会出现相同的数字。
     */
    public static void getSequenceUseSafeSequenceInMultiThread() {
        SafeSequence safeSequence = new SafeSequence();
        // 启用10个线程执行，每个线程生成4个序列
        for (int i = 0; i < 10; i++) {
            Thread t2 = new Thread(() -> {
                for (int j = 0; j < 4; j++) {
                    System.out.print(safeSequence.getValue() + " ");
                }
            });
            t2.start();
        }
    }
}
