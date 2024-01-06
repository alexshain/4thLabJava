package org.example.factory.storages;

import org.example.factory.model.Worker;
import org.example.threadpool.ThreadPool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Thread.currentThread;

public class TaskPoolRunner implements Runnable {
    public int amountOfTasks;
    public int amountOfWorkers;
    public ThreadPool threadPool;
    public int queue;
    public Worker worker;

    public TaskPoolRunner(Worker worker, int amountOfWorkers) {
        this.worker = worker;
        this.amountOfWorkers = amountOfWorkers;
        this.threadPool = new ThreadPool(amountOfWorkers, worker, amountOfTasks);
    }


    @Override
    public void run() {
        while (!currentThread().isInterrupted()) {
            setAmountOfTasks();
            if (threadPool.getActive().equals(0) && amountOfTasks != 0) {
                threadPool = new ThreadPool(amountOfWorkers, worker, amountOfTasks);
                threadPool.run();
            }
            queue = threadPool.getThreadPool().getQueue().size();
        }
    }

    public void setAmountOfTasks() {
        List<Integer> storagesSize = new ArrayList<>();
        storagesSize.add(Integer.parseInt(worker.getBodyStorage().getQuantity()));
        storagesSize.add(Integer.parseInt(worker.getMotorStorage().getQuantity()));
        storagesSize.add(Integer.parseInt(worker.getAccessoryStorage().getQuantity()));
        amountOfTasks = Collections.min(storagesSize);
    }

    public Integer getQueue() {
        return queue;
    }
}
