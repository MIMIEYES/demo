package com.math.shifting;

/**
 * Created by Pierreluo on 2017/7/17.
 */
public class ShiftingTest {
    public static void main(String[] args) {
        int length = -234;
        int a = (length >>> 1);
        System.out.println(a);
        a = (a >> 1);
        System.out.println(a);

        int b = 240;//
        int c = 5;
        int d = b|c;
        System.out.println(d);
    }
}
