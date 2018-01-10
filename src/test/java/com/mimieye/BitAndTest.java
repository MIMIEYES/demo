package com.mimieye;

/**
 * Created by Pierreluo on 2017/6/1.
 */
public class BitAndTest {
    public static void main(String[] args) {
        // 与
        byte a = 15 & 33;
        // 非
        byte b = (byte) +(~a);
        // 或
        byte c = (byte) +(a|b);
        // 异或
        byte d = (byte) +(b^c);

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println(+(~2));

        System.out.println(1 & 1);
        System.out.println(3 & 1);
        System.out.println(7 & 1);
        System.out.println(9 & 3);
        System.out.println(8 & 3);
        System.out.println(7 & 3);
        System.out.println(6 & 3);
        System.out.println(14 & 3);
        System.out.println(3 & 3);
    }
}
