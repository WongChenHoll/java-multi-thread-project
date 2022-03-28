package com.jason.thread.pool.money;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 存/取款任务功能
 *
 * @author WongChenHoll
 * @date 2022-3-1 10:58
 **/
public class WithdrawMoney implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(WithdrawMoney.class);

    private final String userName; // 用户姓名
    private final double money; // 取款金额
    private static double total = 1000.00; // 总金额

    public WithdrawMoney(String userName, double money) {
        this.userName = userName;
        this.money = money;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        logger.info("{} 准备使用[{}]存取钱，存取款金额：[{}]", userName, name, money);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            logger.error("取款异常：", e);
        }
        synchronized (WithdrawMoney.class) { // 使用字节码文件作为锁对象。一定要是同一个锁对象，否则锁不起作用
            if (money > 0) {
                // 存钱，正数
                total += money;
                logger.info("{} 使用[{}]存款[{}元]，当前余额：{}", userName, name, money, total);
            } else {
                // 取钱，负数
                if (-money <= total) {
                    total += money;
                    logger.info("{} 使用[{}]取款成功，取了[{}元]，当前余额：{}", userName, name, -money, total);
                } else {
                    logger.info("余额不足，{} 使用[{}]取钱[{}元]失败，当前余额：{}", userName, name, -money, total);
                }
            }
        }
    }
}
