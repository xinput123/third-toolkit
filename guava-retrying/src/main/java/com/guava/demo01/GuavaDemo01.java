package com.guava.demo01;

import com.github.rholder.retry.*;
import com.google.common.base.Predicates;
import com.guava.util.Logs;
import org.slf4j.Logger;

import java.io.IOException;
import java.time.LocalTime;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class GuavaDemo01 {

    private static final Logger logger = Logs.get();

    /**
     * 定义实现Callable接口的方法，以便 Guava retryer 能够使用
     */
    private static Callable<Boolean> updateReimAgentsCall = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            logger.info("call ..." + LocalTime.now());
            throw new RuntimeException();
//            return true;
        }
    };

    public static void main(String[] args) {
        Retryer<Boolean> retry = RetryerBuilder.<Boolean>newBuilder()
                // 重试条件
                // 抛出runtime异常、checked异常时都会重试，但是抛出error不会重试
                .retryIfException()
                // 判断异常类型是否是IOException类型，如果是,重试
                .retryIfExceptionOfType(IOException.class)
                // 抛出runtime异常时重试
                .retryIfRuntimeException()
                // 判断结果是否是null，如果是,重试
                .retryIfResult(Predicates.isNull())
//                .retryIfResult(Predicates.equalTo(true))

                // 重试策略: 固定等待3秒
                .withWaitStrategy(WaitStrategies.fixedWait(3, TimeUnit.SECONDS))

                // 停止策略: 尝试请求3次
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))

                // 时间限制: 某次请求不得超过2s , 类似: TimeLimiter timeLimiter = new SimpleTimeLimiter();
                .withAttemptTimeLimiter(AttemptTimeLimiters.fixedTimeLimit(2, TimeUnit.SECONDS))

                // 监听器
                .withRetryListener(new GuavaDemo01Listener())
                .build();

        try {
            Boolean result = retry.call(updateReimAgentsCall);
            System.out.println("result = " + result);
        } catch (RetryException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
