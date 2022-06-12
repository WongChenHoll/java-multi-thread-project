package com.jason.thread.base;

/**
 * @author WongChenHoll
 * @date 2022-4-25 星期一 21:28
 **/
public class TestTh {
    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (obj) {
                    System.out.println("t1开始");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1结束");

            }
        });
        Thread t2 = new Thread(()->{
            synchronized (obj){
                System.out.println("开始唤醒");
                obj.notify();
                for (int i = 0; i <5; i++) {
                    System.out.println(i);
                }
            }
        });

        t1.start();
        Thread.sleep(3000);
        t2.start();
    }
}
