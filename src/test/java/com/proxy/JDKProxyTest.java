package com.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Pierreluo on 2017/9/12.
 */
public class JDKProxyTest implements InvocationHandler {

    private Object obj;

    public Object getProxy(Object obj) {
        this.obj = obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("dai li kaishi_.");
        Object result = method.invoke(obj, args);
        System.out.println("dai li jieshu_.");
        return result;
    }
}
