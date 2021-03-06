package com.netty.common;

import io.netty.channel.socket.SocketChannel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
    private final static ExecutorService TASK_POOL = Executors.newFixedThreadPool(1);
    private final static ExecutorService RESULT_POOL = Executors.newFixedThreadPool(1);

    public static void addTask(SocketChannel socketChannel, String msg) {
        TestTask testTask = new TestTask(socketChannel, msg);
        TASK_POOL.submit(testTask);
    }

    public static void addResult(SocketChannel socketChannel, String result) {
        TestResult testResult = new TestResult(socketChannel, result);
        RESULT_POOL.submit(testResult);
    }
}
