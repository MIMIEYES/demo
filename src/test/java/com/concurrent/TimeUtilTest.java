package com.concurrent;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created by Pierreluo on 2017/9/7.
 */
public class TimeUtilTest {
    public static void main(String[] args) {
        System.out.println("s");
        try {
            System.out.println(TimeUnit.SECONDS.toMillis(5));
            System.out.println(Arrays.toString(TimeUnit.values()));
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("e");
    }
}
