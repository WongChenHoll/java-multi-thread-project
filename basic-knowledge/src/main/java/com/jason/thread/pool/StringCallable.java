package com.jason.thread.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

/**
 * @author WongChenHoll
 * @date 2022-2-27 12:34
 **/
public class StringCallable implements Callable<String> {

    private static final Logger logger = LoggerFactory.getLogger(StringCallable.class);

    @Override
    public String call() {
        logger.info("执行Callable类型任务");
        return "俄乌大战";
    }
}
