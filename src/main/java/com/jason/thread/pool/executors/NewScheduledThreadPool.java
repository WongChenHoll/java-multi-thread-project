package com.jason.thread.pool.executors;

import com.jason.thread.pool.ComplexRunnable;
import com.jason.thread.pool.SimpleRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

/**
 * 测试通过使用 Executors.newScheduledThreadPool(5) 创建一个线程池，该线程池具有允许延迟或定期执行任务的功能。
 *
 * @author WongChenHoll
 * @date 2022-2-27 11:17
 **/
public class NewScheduledThreadPool {

    private static final Logger logger = LoggerFactory.getLogger(NewScheduledThreadPool.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        test1();
//        test2();
//        test3();
//        test4();
        test5();
    }

    /**
     * 通过 Executors.newScheduledThreadPool(5) 创建一个有允许延迟或定期执行任务的功能的线程池。
     * 使用 <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit)  添加任务。
     */
    private static void test1() {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);
        logger.info("延迟3秒执行任务，添加任务时间：{}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
        for (int i = 0; i < 10; i++) {
            pool.schedule(new SimpleRunnable(i), 3, TimeUnit.SECONDS); // 延迟 3 秒 执行。
        }
        pool.shutdown();
    }

    /**
     * 通过 Executors.newScheduledThreadPool(3) 创建一个有允许延迟或定期执行任务的功能的线程池。
     * 使用 ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)  添加任务。
     * 如果不手动停止，任务将会一直执行下去。
     */
    private static void test2() {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(3);
        logger.info("延迟2秒执行任务，添加任务时间：{}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
        for (int i = 0; i < 5; i++) {
            // 这里使用了 ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) 方法。
            // 该方法中：command执行的任务、initialDelay：添加任务后延迟多久执行、period：间隔多久重复执行、unit：时间单位。
            // 下面的代码意思是：在线程池中添加任务（根据外面for循环得知共5个任务），添加完成后再延迟2秒开始执行任务，然后每隔5秒再执行一遍线程池中的任务。
            pool.scheduleAtFixedRate(new SimpleRunnable(i), 2, 5, TimeUnit.SECONDS);
        }
        // 注意：这里不能使用shutdown()方法关闭线程池，否则任务将不会执行。
//        pool.shutdown();
    }

    /**
     * 通过 Executors.newScheduledThreadPool(3) 创建一个有允许延迟或定期执行任务的功能的线程池。
     * 使用 ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)  添加任务。
     * 如果不手动停止，任务将会一直执行下去。这里的任务换成ComplexRunnable对象。将间隔时间换成了10秒。
     */
    private static void test3() {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(3);
        logger.info("延迟2秒执行任务，添加任务时间：{}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
        for (int i = 0; i < 5; i++) {
            // 同上面test2()方法中的一样，这里任务对象换成ComplexRunnable
            // 可以看到，每个任务执行的开始时间都是相差10秒。
            pool.scheduleAtFixedRate(new ComplexRunnable(i), 2, 10, TimeUnit.SECONDS);
        }
        // 注意：这里不能使用shutdown()方法关闭线程池，否则任务将不会执行。
//        pool.shutdown();
    }

    /**
     * 通过 Executors.newScheduledThreadPool(3) 创建一个有允许延迟或定期执行任务的功能的线程池。
     * 这里使用了 ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit)  方法添加任务。
     * 线程池核心线程数是3。共添加5个任务。
     * 如果不手动停止，任务将会一直执行下去。
     * 注意这里的间隔时间是指同一个任务的“结束到开始”之间的时间间隔。
     */
    private static void test4() {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(3);
        logger.info("延迟2秒执行任务，添加任务时间：{}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
        for (int i = 0; i < 5; i++) {
            // 这里使用了 ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit)  方法。
            // 该方法中：command执行的任务、initialDelay：添加任务后延迟多久执行、delay：间隔多久重复执行，在每一次执行终止和下一次执行开始之间、unit：时间单位。
            // 下面的代码意思是：在线程池中添加任务（根据外面for循环得知共5个任务），添加完成后再延迟2秒开始执行任务，然后每隔10秒再执行一遍线程池中的任务。
            pool.scheduleWithFixedDelay(new ComplexRunnable(i), 2, 10, TimeUnit.SECONDS);
        }
        // 注意：这里不能使用shutdown()方法关闭线程池，否则任务将不会执行。
//        pool.shutdown();
    }

    /**
     * 使用：static ScheduledExecutorService newScheduledThreadPool(int corePoolSize, ThreadFactory threadFactory) 方法创建线程池。
     * 使用：ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) 添加任务。
     * ThreadFactory和Callable<V>对象均使用lambda表达式。
     */
    private static void test5() throws ExecutionException, InterruptedException {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(3, r -> new Thread(r, "自定义线程"));
        ScheduledFuture<String> future = pool.schedule(() -> "任务执行完毕！", 5, TimeUnit.SECONDS);
        logger.info("方法test5()中任务执行的结果：{}", future.get());
        pool.shutdown();
    }
}
