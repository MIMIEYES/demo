package com.string;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * Created by Pierreluo on 2017/8/9.
 */
public class StringEscapeTest {

    public static void main(String[] args) {
        String a0 = "&lt;xml&gt;asd&lt;/xml&gt;";
        String result = StringEscapeUtils.unescapeHtml(a0);
        System.out.println(result);
        result = StringEscapeUtils.unescapeJava(a0);
        System.out.println(result);
        result = StringEscapeUtils.unescapeXml(a0);
        System.out.println(result);
        result = StringEscapeUtils.unescapeJavaScript(a0);
        System.out.println(result);
    }
}
