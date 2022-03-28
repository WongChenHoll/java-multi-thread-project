package com.jason.thread.pool.goods;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 手机秒杀活动，总共10个手机
 *
 * @author WongChenHoll
 * @date 2022-3-1 14:21
 **/
public class PhoneSecondsKill implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(PhoneSecondsKill.class);

    private static int phoneNum = 10;
    private final String userName; // 用户

    public PhoneSecondsKill(String userName) {
        this.userName = userName;
    }

    @Override
    public void run() {
        synchronized (PhoneSecondsKill.class) { // 使用字节码文件作为锁对象
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                logger.error("秒杀活动异常：", e);
            }
            if (phoneNum > 0) {
                phoneNum--;
                logger.info("恭喜 {} 抢到手机一部！", userName);
            }else{
                logger.info("手机已售空！");
            }
        }
    }
}
