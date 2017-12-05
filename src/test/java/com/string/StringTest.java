package com.string;

import java.io.UnsupportedEncodingException;

/**
 * Created by Pierreluo on 2017/7/19.
 */
public class StringTest {
    public static void main(String[] args) throws UnsupportedEncodingException {

        String asd = "asd";
        String qwe = new String("asd");
        System.out.println(asd == qwe);

        System.out.println(asd == qwe.intern());
        String utf_8="hah哈哈哈哈哈";
        String gbk=new String(utf_8.getBytes(),"gbk");
        System.out.println(gbk);





    }

}
