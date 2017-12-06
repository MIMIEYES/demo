package com.concurrent.threadpool;

import java.util.concurrent.TimeUnit;

/**
 * Created by Pierreluo on 2017/12/6.
 */
public class BulkExecuteJob implements Runnable {
    private String id;
    public BulkExecuteJob(String id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            System.out.println("开始执行任务：" + id);
            //TODO execute really task with data ID
            TimeUnit.SECONDS.sleep(3);
            System.out.println("结束任务：" + id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}
