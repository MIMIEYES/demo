package com.entity;


import lombok.Getter;
import lombok.Setter;

/**
 * Created by Pierreluo on 2017/6/23.
 */
@Getter
@Setter
public class TestBean {
    private String msg;
    private String cmtcod;

    public String getMsg() {
        return msg;
    }


    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCmtcod() {
        return cmtcod;
    }

    public void setCmtcod(String cmtcod) {
        this.cmtcod = cmtcod;
    }

    private void testm() {
        System.out.println("testm");
    }

    private static void tests() {
        System.out.println("tests");
    }
}
