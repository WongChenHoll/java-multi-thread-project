package com.jason.thread.pool.executors;

import com.jason.thread.pool.ComplexRunnable;
import com.jason.thread.pool.SimpleRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

/**
 * 通过 Executors.newSingleThreadScheduledExecutor() 创建一个单线程执行程序，它支持在给定延迟后运行命令或定期执行。
 *
 * @author WongChenHoll
 * @date 2022-2-27 12:36
 **/
public class NewSingleThreadScheduledExecutor {

    private static final Logger logger = LoggerFactory.getLogger(NewSingleThreadScheduledExecutor.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        test1();
//        test2();
//        test3();
        test4();
    }

    /**
     * 使用 Executors.newSingleThreadScheduledExecutor() 创建一个单线程执行程序。
     * 通过 ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) 添加具有延时执行任务的效果。
     */
    private static void test1() throws ExecutionException, InterruptedException {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        logger.info("程序开始时间：{}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
        for (int i = 0; i < 5; i++) {
            // 任务延迟3秒执行
            ScheduledFuture<?> future = executor.schedule(new SimpleRunnable(i), 3, TimeUnit.SECONDS);
            logger.info("{},{}", future.isDone(), future.get());
        }
        executor.shutdown();
    }

    /**
     * 使用 Executors.newSingleThreadScheduledExecutor() 创建一个单线程执行程序。
     * 通过 ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) 添加具有延时执行任务的效果。
     */
    private static void test2() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        logger.info("程序开始时间：{}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
        // 任务延迟3秒执行
        executor.scheduleAtFixedRate(new SimpleRunnable(10), 3, 5, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(new SimpleRunnable(20), 3, 5, TimeUnit.SECONDS);
    }

    /**
     * 使用 Executors.newSingleThreadScheduledExecutor() 创建一个单线程执行程序。
     * 通过 ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) 添加具有延时执行任务的效果。
     */
    private static void test3() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        logger.info("程序开始时间：{}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
        // 任务延迟3秒执行
        executor.scheduleAtFixedRate(new ComplexRunnable(11), 3, 20, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(new ComplexRunnable(22), 3, 20, TimeUnit.SECONDS);
    }

    /**
     * 使用 Executors.newSingleThreadScheduledExecutor() 创建一个单线程执行程序。
     * 通过 ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long period, TimeUnit unit) 添加具有延时执行任务的效果。
     * <p>
     * 测试例子解释：
     * 1.使用newSingleThreadScheduledExecutor创建一个单线程执行的程序。
     * 2.添加了两个ComplexRunnable类型的任务，该类型任务执行耗时是5秒。
     * 3.通过scheduleWithFixedDelay()方法添加任务，本例子中表示：本次添加两个任务，任务编号分别是11和22。在任务添加完成后延迟3秒开始执行任务。
     * 首先执行的是先添加的任务（任务编号11），然后再执行后添加的任务（任务编号22）。
     * 当任务编号11执行完成后，20秒后再执行一次，任务编号22执行完成后，20秒之后再执行一次
     * 依次进行下去......
     */
    private static void test4() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        logger.info("程序开始时间：{}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
        // 任务延迟3秒执行
        executor.scheduleWithFixedDelay(new ComplexRunnable(11), 3, 20, TimeUnit.SECONDS);
        executor.scheduleWithFixedDelay(new ComplexRunnable(22), 3, 20, TimeUnit.SECONDS);
    }
}
