## guava-retrying 简介

### RetryerBuilder
RetryerBuilder 是一个factory创建者，可以定制设置重试
源且可以支持多个重试源，可以配置重试次数或重试超时时间，
以及可以配置等待时间间隔，创建重试者 Retryer 实例。

RetryerBuilder 的重试源支持 Exception 异常对象和自定
义断言对象，通过retryIfException 和 retryIfResult 
设置，同时支持多个且能兼容。

- retryIfException: 抛出 runtime 异常、checked 异常时都会重试，但是抛出 error 不会重试.
- retryIfRuntimeException: 只会在抛 runtime 异常的时候才重试，checked 异常和error 都不重试。
- retryIfExceptionOfType: 允许我们只在发生特定异常的时候才重试，比如NullPointerException 和 IllegalStateException 都属于 runtime 异常，也包括自定义的error。

```$xslt
// 只在抛出error重试
retryIfExceptionOfType(Error.class)

// 在只有出现指定的异常的时候才重试
.retryIfExceptionOfType(IllegalStateException.class)
.retryIfExceptionOfType(NullPointerException.class)  

// 或者通过Predicate实现
.retryIfException(Predicates.or(Predicates.instanceOf(NullPointerException.class),
Predicates.instanceOf(IllegalStateException.class))) 
```

- retryIfResult: 指定你的 Callable 方法在返回值的时候进行重试

```$xslt
// 返回false重试  
.retryIfResult(Predicates.equalTo(false))   

//以_error结尾才重试  
.retryIfResult(Predicates.containsPattern("_error$"))  
```

- RetryListener: 当发生重试之后，假如我们需要做一些额外的处理动作，比如log一下异常，那么可以使用RetryListener。
每次重试之后，guava-retrying 会自动回调我们注册的监听。

可以注册多个RetryListener，会按照注册顺序依次调用。  

```$xslt
.withRetryListener(new RetryListener {      
 @Override    
   public <T> void onRetry(Attempt<T> attempt) {  
               logger.error("第【{}】次调用失败" , attempt.getAttemptNumber());  
          } 
 }
) 
```

### 主要接口
| 接口 | 描述 | 备注
| --- | --- | --- 
| AttemptTimeLimiter | 单次任务执行时间限制 | 如果单次任务执行超时，则终止执行当前任务
| BlockStrategies | 任务阻塞策略 | 通俗的讲就是当前任务执行完，下次任务还没开始这段时间做什么），默认策略为：BlockStrategies.THREAD_SLEEP_STRATEGY
| RetryException | 重试异常 | 
| RetryListener | 自定义重试监听器 | 可以用于异步记录错误日志
| StopStrategy | 停止重试策略 | 
| WaitStrategy | 等待时长策略 |（控制时间间隔），返回结果为下次执行时长
 
 
### StopStrategy
- StopAfterDelayStrategy:设定一个最长允许的执行时间；比如设定最长执行10s，无论任务执行次数，只要重试的时候超出了最长时间，则任务终止，并返回重试异常RetryException；
- NeverStopStrategy: 不停止，用于需要一直轮训知道返回期望结果的情况；
- StopAfterAttemptStrategy: 设定最大重试次数，如果超出最大重试次数则停止重试，并返回重试异常；

### WaitStrategy
- FixedWaitStrategy: 固定等待时长策略
- RandomWaitStrategy: 随机等待时长策略（可以提供一个最小和最大时长，等待时长为其区间随机值）
- IncrementingWaitStrategy: 递增等待时长策略（提供一个初始值和步长，等待时间随重试次数增加而增加）
- ExponentialWaitStrategy: 指数等待时长策略
- FibonacciWaitStrategy: Fibonacci 等待时长策略
- ExceptionWaitStrategy: 异常时长等待策略
- CompositeWaitStrategy: 复合时长等待策略