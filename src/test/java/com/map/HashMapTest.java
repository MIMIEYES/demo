package com.map;

import java.util.HashMap;

/**
 * Created by Pierreluo on 2017/7/19.
 */
public class HashMapTest {
    public static void main(String[] args) {
        HashMap map = new HashMap(3);
        map.put("a", 1);
        System.out.println(map.get("a"));
    }
}
