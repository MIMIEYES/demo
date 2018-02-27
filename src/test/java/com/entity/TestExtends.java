package com.entity;

import java.util.concurrent.TimeUnit;

public class TestExtends extends TestBean {
    public static void tests() {
        System.out.println("zi-tests");
    }

    public static void main(String[] args) {
        TestExtends.tests();
        Runnable run = new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < 5) {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " = " + (++i));
                }
            }
        };
        Thread thread = new Thread(run);
        //thread.setDaemon(true);
        thread.start();
    }
}
