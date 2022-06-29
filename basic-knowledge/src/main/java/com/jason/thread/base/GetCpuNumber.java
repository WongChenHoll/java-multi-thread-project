package com.jason.thread.base;

/**
 * 获取CPU的数量
 *
 * @author WongChenHoll
 * @date 2022-6-28 星期二 17:25
 **/
public class GetCpuNumber {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("CPU的数量：" + runtime.availableProcessors());
        System.out.println("  总 内存大小：" + runtime.totalMemory() + " bytes，" + runtime.totalMemory() / 1024 / 1024 + "M");
        System.out.println("空闲内存的大小：" + runtime.freeMemory() + " bytes，" + runtime.freeMemory() / 1024 / 1024 + "M");
        System.out.println("最  大内存大小：" + runtime.maxMemory() + " bytes，" + runtime.maxMemory() / 1024 / 1024 + "M");
    }
}
