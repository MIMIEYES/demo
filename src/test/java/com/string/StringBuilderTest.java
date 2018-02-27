package com.string;

/**
 * Created by Pierreluo on 2017/7/21.
 */
public class StringBuilderTest {
    public static void main(String[] args) {
        String asd = "123456";
        asd = asd.substring(asd.length() - 3);
        StringBuilder sb = new StringBuilder();
        sb.append(asd);
        System.out.println(sb.toString());
    }
}
