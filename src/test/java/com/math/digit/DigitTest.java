package com.math.digit;

import java.math.BigDecimal;

/**
 * Created by Pierreluo on 2017/7/26.
 */
public class DigitTest {
    public static void main(String[] args) {
        System.out.println(5.0942 * 1000);
        float a = (float) (5.0943 * 1000);
        float b = (float) (5.0944 * 1000);

        Object oa = 5.0943 * 1000;
        Object ob = 5.0944 * 1000;

        System.out.println(oa);
        System.out.println(ob);
        //new BigDecimal("").add().subtract().multiply().divide();
    }
}
