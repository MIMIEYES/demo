package com.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by Pierreluo on 2017/9/12.
 */
public class CglibProxyTest implements MethodInterceptor{

    private Enhancer en = new Enhancer();

    public Object getProxy(Class clazz) {
        en.setSuperclass(clazz);
        en.setCallback(this);
        return en.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("dai li kaishi.");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("dai li jieshu.");
        return result;
    }
}
