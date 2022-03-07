package com.jason.thread.state;

/**
 * 测试如下方法：
 * <pre>
 *     1.interrupt().Thread类中的成员方法，public void interrupt()；给线程打上中断标识。
 *          a.如果线程进入阻塞状态（例如sleep,wait,join等），则会抛出异常：java.lang.InterruptedException: sleep interrupted。
 *          b.如果线程处于正常活动状态，那么该线程的中断标识为true，不会影响线程的正常运行。
 *     2.interrupted().Thread类中的静态方法，public static boolean interrupted()；判断当前线程的中断状态。
 *          注意的是第二次调用此方法时会清除中断状态。当调用interrupt()方法后，也就是第一次调用interrupted()会返回true，第二次调用就会返回false。
 *     3.isInterrupted().Thread类中的成员方法，public boolean isInterrupted() ；判断当前线程的中断状态。不会清除中断状态。
 *     4.isInterrupted(boolean ClearInterrupted).
 * </pre>
 *
 * @author WongChenHoll
 * @date 2022-3-4 15:49
 **/
public class TestInterrupt {

    public static void main(String[] args) throws InterruptedException {
//        testIsInterruptedWithoutSleep();
//        testIsInterruptedAfterSleep();
        testInterrupted();
//        testIsInterruptedWithSleep();
    }

    /**
     * 测试一个线程在执行过程中的中断状态。
     * 在调用interrupt()方法之前，使用 isInterrupted()查看该线程的中断状态是false，
     * 调用interrupt()之后使用 isInterrupted()查看该线程的中断状态一直是true。
     */
    private static void testIsInterruptedWithoutSleep() {
        Thread ss = new Thread(() -> {
            long s = System.currentTimeMillis();
            while (System.currentTimeMillis() - s < 3000) {
            }
        });
        ss.start();
        System.out.println("ss 调用interrupt()之前，中断状态：" + ss.isInterrupted() + "，存活：" + ss.isAlive() + "，线程状态：" + ss.getState());
        ss.interrupt();
        System.out.println("ss 调用interrupt()之后，测试调用isInterrupted()方法");
        System.out.println("ss 第一次中断状态：" + ss.isInterrupted() + "，存活：" + ss.isAlive() + "，线程状态：" + ss.getState());
        System.out.println("ss 第二次中断状态：" + ss.isInterrupted() + "，存活：" + ss.isAlive() + "，线程状态：" + ss.getState());
        System.out.println("ss 第三次中断状态：" + ss.isInterrupted() + "，存活：" + ss.isAlive() + "，线程状态：" + ss.getState());
    }

    /**
     * 测试一个线程在执行过程中的中断状态。
     * 同方法{@link TestInterrupt#testIsInterruptedWithoutSleep()}结果一样。
     * 虽然在本方法中使用了Thread.sleep(1000)方法使线程睡眠了1秒，但是该方法针对的是当前主线程，也就是main线程，所以对bb线程没影响。
     * 但是main的中断效果和主线程和方法{@link TestInterrupt#testIsInterruptedWithoutSleep()}中的ss线程一样。
     */
    private static void testIsInterruptedAfterSleep() throws InterruptedException {
        Thread bb = new Thread(() -> {
            long s = System.currentTimeMillis();
            while (System.currentTimeMillis() - s < 3000) {
            }
        });
        bb.start();
        System.out.println("bb 调用interrupt()之前，中断状态：" + bb.isInterrupted() + "，存活：" + bb.isAlive() + "，线程状态：" + bb.getState());
        Thread.sleep(1000);
        bb.interrupt();
        System.out.println("bb 调用interrupt()之后，测试调用isInterrupted()方法");
        System.out.println("bb 第一次中断状态：" + bb.isInterrupted() + "，存活：" + bb.isAlive() + "，线程状态：" + bb.getState());
        System.out.println("bb 第二次中断状态：" + bb.isInterrupted() + "，存活：" + bb.isAlive() + "，线程状态：" + bb.getState());
        System.out.println("bb 第三次中断状态：" + bb.isInterrupted() + "，存活：" + bb.isAlive() + "，线程状态：" + bb.getState());

        Thread currentThread = Thread.currentThread(); // 当前主线程
        System.out.println("当前主线程：" + currentThread.getName());
        System.out.println("测试的主线程中断状态：" + currentThread.isInterrupted() + "，存活：" + currentThread.isAlive() + "，线程状态：" + currentThread.getState());
        currentThread.interrupt();
//        Thread.sleep(2000); // 加上这段代码，让主线程睡眠2秒，也就是阻塞了，此时会报异常。注意：这里的主线程是同步执行的。
        System.out.println("主线程 第一次中断状态：" + currentThread.isInterrupted() + "，存活：" + currentThread.isAlive() + "，线程状态：" + currentThread.getState());
        System.out.println("主线程 第二次中断状态：" + currentThread.isInterrupted() + "，存活：" + currentThread.isAlive() + "，线程状态：" + currentThread.getState());
        System.out.println("主线程 第三次中断状态：" + currentThread.isInterrupted() + "，存活：" + currentThread.isAlive() + "，线程状态：" + currentThread.getState());
    }

    /**
     * 测试主线程的中断状态。
     * 在主线程调用interrupt()方法给线程打上中断标识之前，使用 Thread.interrupted()查看该线程的中断状态是false，
     * 调用interrupt()之后，使用 Thread.interrupted()查看该线程的中断状态第一次是true，后面再查看就是false。
     */
    private static void testInterrupted() throws InterruptedException {
        Thread currThread = Thread.currentThread(); // 当前主线程
        System.out.println("当前主线程：" + currThread.getName());
        System.out.println("测试的主线程中断状态：" + Thread.interrupted() + "，存活：" + currThread.isAlive() + "，线程状态：" + currThread.getState());
        currThread.interrupt();
//        Thread.sleep(2000); // 加上这段代码，让主线程睡眠2秒，也就是阻塞了，此时会报异常。
        System.out.println("主线程 第一次中断状态：" + Thread.interrupted() + "，存活：" + currThread.isAlive() + "，线程状态：" + currThread.getState());
        System.out.println("主线程 第二次中断状态：" + Thread.interrupted() + "，存活：" + currThread.isAlive() + "，线程状态：" + currThread.getState());
        System.out.println("主线程 第三次中断状态：" + Thread.interrupted() + "，存活：" + currThread.isAlive() + "，线程状态：" + currThread.getState());
    }

    /**
     * 测试 中断sleep()方法
     * 当线程中使用sleep阻塞线程时，调用 interrupt()后，首先会使线程的中断状态设为true，随后会立马改为false。
     * 然后抛出异常。抛出异常之后线程的中断状态为false，抛出异常之前可能会存在true和false的情况。
     */
    private static void testIsInterruptedWithSleep() {
        // 线程中使用sleep()方法
        Thread aa = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("aa 线程处于阻塞状态，中断时异常:" + e);
            }
        });
        aa.start();
        System.out.println("aa 调用interrupt()之前，中断状态：" + aa.isInterrupted() + "，存活：" + aa.isAlive() + "，线程状态：" + aa.getState());
        aa.interrupt();
        System.out.println("aa 第一次中断状态：" + aa.isInterrupted() + "，存活：" + aa.isAlive() + "，线程状态：" + aa.getState());
        System.out.println("aa 第二次中断状态：" + aa.isInterrupted() + "，存活：" + aa.isAlive() + "，线程状态：" + aa.getState());
        System.out.println("aa 第三次中断状态：" + aa.isInterrupted() + "，存活：" + aa.isAlive() + "，线程状态：" + aa.getState());
    }
}
