package com.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Pierreluo on 2017/9/26.
 */
public class ListTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        for (String item : list) {
            if ("2".equals(item)) {
                list.remove(item);
            }
        }
        System.out.println(Arrays.toString(list.toArray()));
    }
}
