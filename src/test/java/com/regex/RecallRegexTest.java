package com.regex;

import com.interfaces.RunInterface;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecallRegexTest implements RunInterface{
    public static void main(String[] args) {
        new RecallRegexTest().run();
    }

    @Override
    public void run() {
        //独占模式，带'+'号，把目标字符串中所有 b 匹配完，后面的 b 再与 c 比较，即返回 false
        Pattern p = Pattern.compile("ab{1,3}+bc");
        String eg = "abbc";
        Matcher m = p.matcher(eg);
        System.out.println(m.find());
        //若改为'abbbbc'，则 b{1,3}将匹配前3个 b，后面的 bc 与eg 中的 bc 比较，即返回 true
        eg = "abbbbc";
        m = p.matcher(eg);
        System.out.println(m.find());
        // test vim
        //TODO
    }
}