package com.mimieye;

import org.apache.commons.lang.StringUtils;

/**
 * Created by Pierreluo on 2017/5/22.
 */
public class UnicodeTest {
    public static void main(String[] args) {
        System.out.println(Integer.toHexString('羅'));

    }
    public static String string2Unicode(String string) {

        if(StringUtils.isBlank(string))return null;
        StringBuffer unicode = new StringBuffer();

        for (int i = 0; i < string.length(); i++) {

            // 取出每一个字符
            char c = string.charAt(i);

            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }

        return unicode.toString();
    }

    public static String unicode2String(String unicode){
        if(StringUtils.isBlank(unicode))return null;
        StringBuilder sb = new StringBuilder();
        int i = -1;
        int pos = 0;


        while((i=unicode.indexOf("\\u", pos)) != -1){
            sb.append(unicode.substring(pos, i));
            if(i+5 < unicode.length()){
                pos = i+6;
                sb.append((char)Integer.parseInt(unicode.substring(i+2, i+6), 16));
            }
        }


        return sb.toString();
    }
}
