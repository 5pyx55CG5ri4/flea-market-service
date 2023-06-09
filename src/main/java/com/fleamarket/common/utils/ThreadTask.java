package com.fleamarket.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 */
@Slf4j
public class ThreadTask {
    private static final ThreadTask THREAD_TASK = new ThreadTask();
    /**
     * 固定线程池
     */
    private final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private ThreadTask() {
    }

    public static ThreadTask getInstance() {
        return THREAD_TASK;
    }

    /**
     * 加入线程池执行
     *
     * @param runnable 执行线程
     */
    public void addTask(Runnable runnable) {
        try {
            executorService.execute(runnable);
        } catch (Exception e) {
            log.error("ThreadPool execute error", e);
        }
    }
}
