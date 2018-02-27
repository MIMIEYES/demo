package com.overload;

public class OverloadTest {
    public static void main(String[] args) {
        OverloadTest test = new OverloadTest();
        Object obj = "asd";
        test.test1(obj);
        test.test1("a");
    }

    public void test1(Object obj) {
        System.out.println("obj method.");
    }

    public void test1(String str) {
        System.out.println("string method.");
    }
}
