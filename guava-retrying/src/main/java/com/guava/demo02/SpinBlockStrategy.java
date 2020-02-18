package com.guava.demo02;

import com.github.rholder.retry.BlockStrategy;
import com.guava.util.Logs;
import org.slf4j.Logger;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * 自定义重试阻塞策略
 * 自旋锁的实现，不响应线程终端
 */
public class SpinBlockStrategy implements BlockStrategy {

    private static final Logger logger = Logs.get();

    @Override
    public void block(long sleepTime) throws InterruptedException {
        LocalDateTime startTime = LocalDateTime.now();

        long start = System.currentTimeMillis();
        long end = start;

        logger.info("[SpinBlockStrategy]...begin wait.");

        while (end - start <= sleepTime) {
            end = System.currentTimeMillis();
        }

        //使用Java8新增的Duration计算时间间隔
        Duration duration = Duration.between(startTime, LocalDateTime.now());

        logger.info("[SpinBlockStrategy]...end wait.duration={}", duration.toMillis());

    }
}
