package com.tengol.practice.sb.common.util;

import java.util.concurrent.*;

/**
 * ThreadPoolUtil
 *
 * @author dongrui
 * @date 2021/5/28 13:01
 */
public class ThreadPoolUtil {
    private final static int MAX_CORE_POOL_SIZE = 8;
    private final static int DEFAULT_CORE_POOL_SIZE = 4;

    public static ExecutorService getThreadPool() {
        // 由于 Runtime.getRuntime().availableProcessors() 在容器获取 CPU 核数错误，故指定线程默认为 4
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        int corePoolSize = availableProcessors > MAX_CORE_POOL_SIZE ? DEFAULT_CORE_POOL_SIZE : availableProcessors;
        int maximumPoolSize = corePoolSize * 2;

        return getThreadPool(corePoolSize, maximumPoolSize);
    }

    /**
     * 创建默认线程池
     *
     * @return 线程池
     */
    public static ExecutorService getThreadPool(int corePoolSize, int maximumPoolSize) {
        // 自定义线程池的参数
        long keepAliveTime = 300;
        TimeUnit timeUnit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>(corePoolSize);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        ThreadPoolExecutor.CallerRunsPolicy callerRunsPolicy = new ThreadPoolExecutor.CallerRunsPolicy();

        // 创建线程池
        return new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                timeUnit,
                blockingQueue,
                threadFactory,
                callerRunsPolicy);
    }
}
