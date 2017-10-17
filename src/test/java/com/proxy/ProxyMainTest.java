package com.proxy;

/**
 * Created by Pierreluo on 2017/9/12.
 */
public class ProxyMainTest {

    static void demoJDK() {
        JDKProxyTest jdkProxyTest = new JDKProxyTest();
        DoPrintInterface doPrint = new DoPrint();
        DoPrintInterface doProxy = (DoPrintInterface) jdkProxyTest.getProxy(doPrint);
        doProxy.printOut();
    }
    static void demoCglib() {
        CglibProxyTest cglibProxyTest = new CglibProxyTest();
        DoPrintInterface doProxy = (DoPrintInterface) cglibProxyTest.getProxy(DoPrint.class);
        doProxy.printOut();
    }

    public static void main(String[] args) {
        demoJDK();
        demoCglib();
    }
}
