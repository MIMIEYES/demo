package com.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Created by Pierreluo on 2017/12/13.
 */
public class ThreadTest {
    private final Object obj = new Object();
    private boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        ThreadTest f = new ThreadTest();
        //test.main0();
        new Thread(
                new Runnable(){

                    @Override
                    public void run() {
                        for(int i=0;i<5;i++){
                            f.sub();
                        }
                    }
                }
        ).start();
        for(int i=0;i<5;i++){
            f.main();
        }
    }


    public void main0() throws InterruptedException {

        int i = 0;
        Thread thread = null;
        while(true) {
            thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    /*try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                    while (true) {
                        for(int i=0; i < 10; i++) {
                            System.out.println("sub"+i);
                        }
                    }
                }
            });
            thread.start();
            synchronized (obj) {
                for(int o=0; o < 20; o++) {
                    System.out.println("main"+o);
                }
            }

            if(++i == 2) {
                break;
            }
        }
    }

    public synchronized void sub() {
        while (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 10; i++) {
//for循环内定义子线程的功能,这里简单的假设为打印一句话,主线程同理
            System.out.println("sub" + i);
        }
        flag = true;
        this.notify();
    }

    //主线程要实现的功能
    public synchronized void main() {
        while (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 20; i++) {
            System.out.println("main" + i);
        }
        flag = false;
        this.notify();
    }
//}
}

