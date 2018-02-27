package com.temp;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class ThreadTest {
    public static void main(String[] args) {
        final ThreadTest threadTest = new ThreadTest();
        Runnable run = new Runnable() {
            @Override
            public void run() {
                threadTest.test();
            }
        };
        Thread aaa = new Thread(run, "aaa");
        Thread bbb = new Thread(run, "bbb");
        System.out.println("aaa调用test");
        aaa.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("bbb调用test");
        bbb.start();

    }

    private boolean t = false;
    public synchronized void test() {
        System.out.println(Thread.currentThread().getName() + " - test method...");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(!t) {
            t = true;

            test();
        }
    }
}
