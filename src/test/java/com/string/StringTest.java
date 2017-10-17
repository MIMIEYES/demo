package com.string;

/**
 * Created by Pierreluo on 2017/7/19.
 */
public class StringTest {
    public static void main(String[] args) {
        String asd = "asd";
        String qwe = new String("asd");
        System.out.println(asd == qwe);
        System.out.println(asd == qwe.intern());
    }
}
