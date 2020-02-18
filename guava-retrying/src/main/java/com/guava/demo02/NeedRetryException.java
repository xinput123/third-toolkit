package com.guava.demo02;

/**
 * 自定义异常: 当抛出这个异常的时候，表示需要重试
 */
public class NeedRetryException extends Exception {
    public NeedRetryException(String message) {
        super("NeedRetryException can retry." + message);
    }
}
