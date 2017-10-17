package com.enumpackge;


import lombok.Getter;

/**
 * Created by Pierreluo on 2017/9/8.
 */
@Getter
public enum EnumTest {
    AD(1, "ASD"), SD(2, "SFD"), DF(3, "DGF");

    public void asd() {
        for(EnumTest t : values()) {
            System.out.println(t.ordinal() + " - " + t.name());
        }
    }

    private int status;
    private String desc;

    EnumTest(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }


    public static void main(String[] args) {
        System.out.println(EnumTest.AD.getStatus());
        System.out.println(EnumTest.AD.getDesc());
    }
}