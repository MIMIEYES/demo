package com.singleton;

/**
 * Created by Pierreluo on 2017/9/8.
 */
public class InnerClassSingleton {
    private static final class SingletonObject {
        public static final InnerClassSingleton INNER_CLASS_SINGLETON = new InnerClassSingleton();
    }
    private InnerClassSingleton() {}

    public InnerClassSingleton getInstance() {

        System.currentTimeMillis();
        return SingletonObject.INNER_CLASS_SINGLETON;

    }
}
