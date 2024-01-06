package org.example.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public final class ThreadPool {
    public Runnable worker;
    public ThreadPoolExecutor executor;
    public int amountOfTasks;
    public int queue;

    public ThreadPool(int nThreads, Runnable worker, int amountOfTasks) {
        this.worker = worker;
        this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(nThreads);
        this.amountOfTasks = amountOfTasks;
    }

    public void run() {
        for (int i = 0; i < amountOfTasks; i++) {
            executor.submit(worker);
        }
        executor.shutdown();
        queue = executor.getQueue().size();
    }

    public ThreadPoolExecutor getThreadPool() {
        return executor;
    }

    public Integer getActive() {
        return executor.getActiveCount();
    }
}

