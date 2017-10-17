package com.math.bigdecimal;

import java.math.BigDecimal;

/**
 * Created by Pierreluo on 2017/7/26.
 */
public class BigDecimalAddTest {
    public static void main(String[] args) {
        BigDecimal seed = BigDecimalConstant.SEED;
        BigDecimal a = new BigDecimal("5.0942");
        System.out.println(a.add(seed));
    }
}
