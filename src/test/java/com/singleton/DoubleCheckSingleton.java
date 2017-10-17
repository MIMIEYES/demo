package com.singleton;

/**
 * Created by Pierreluo on 2017/9/8.
 */
public class DoubleCheckSingleton {
    private static volatile DoubleCheckSingleton DOUBLE_CHECK_SINGLETON = null;
    private DoubleCheckSingleton() {}

    public DoubleCheckSingleton getInstance() {
        if(DOUBLE_CHECK_SINGLETON == null) {
            synchronized (this) {
                if(DOUBLE_CHECK_SINGLETON == null) {
                    DOUBLE_CHECK_SINGLETON = new DoubleCheckSingleton();
                }
            }
        }
        return DOUBLE_CHECK_SINGLETON;
    }
}
