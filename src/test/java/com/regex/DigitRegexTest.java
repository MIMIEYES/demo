package com.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Pierreluo on 2017/8/18.
 */
public class DigitRegexTest {
    public static void main(String[] args) {
        String todo = "111111";
        Pattern pattern = Pattern.compile("^([\\d])\\1{5}$");
        Matcher matcher = pattern.matcher(todo);
        System.out.println(matcher.find());

    }
}
