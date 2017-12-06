package com.concurrent.threadpool;

/**
 * Created by Pierreluo on 2017/12/6.
 */
public interface ThreadPool<Job extends Runnable> {
    void execute(Job job);
    void shutdown();
    void addWorkers(int num);
    void removeWorkers(int num);
    int getJobSize();
}
