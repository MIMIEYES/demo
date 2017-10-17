package com.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Pierreluo on 2017/7/17.
 */
public class CacheBuilderTest {

    private static LoadingCache<String, Object> build;
    static {
        build = CacheBuilder.newBuilder()
                .expireAfterAccess(1, TimeUnit.SECONDS)
                .build(new CacheLoader<String, Object>() {
                    @Override
                    public Object load(String key) throws Exception {
                        return key + "'s value - " + System.currentTimeMillis();
                    }
                });
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int i = 0;
        while(true) {
            i++;
            TimeUnit.SECONDS.sleep(2);
            System.out.println(build.get("a"));
            if (i > 6) {
                break;
            }
        }
    }

}
