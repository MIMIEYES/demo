package com.math.bigdecimal;

import java.math.BigDecimal;

/**
 * Created by Pierreluo on 2017/7/26.
 */
public class BigDecimalSubtractTest {
    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("5.0943");
        System.out.println(a.subtract(BigDecimalConstant.SEED));
    }
}
