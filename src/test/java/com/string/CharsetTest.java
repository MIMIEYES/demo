package com.string;

import java.io.UnsupportedEncodingException;

/**
 * Created by Pierreluo on 2017/8/22.
 */
public class CharsetTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "字符集测试";
        System.out.println(str);

        String a0 = new String(str.getBytes("GBK"), "GBK");
        System.out.println(a0);
        String a1 = new String(a0.getBytes("UTF-8"), "GBK");
        System.out.println(a1);
        String a2 = new String(a1.getBytes("GBK"), "UTF-8");
        System.out.println(a2);
        byte[] bytes = a0.getBytes("GBK");
        String a3 = new String(bytes, "ISO-8859-1");
        System.out.println(a3);
        String a4 = new String(a3.getBytes("ISO-8859-1"), "GBK");
        System.out.println(a4);

    }
}
