package com.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.entity.TestBean;

/**
 * Created by Pierreluo on 2017/6/23.
 */
public class FastjsonTest {
    public static void main(String[] args) {
        TestBean tb = new TestBean();
        tb.setCmtcod("xxxxxx");
        tb.setMsg("asdasdasd");
        System.out.println(JSON.toJSONString(tb, false));
    }
}
