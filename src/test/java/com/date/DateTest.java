package com.date;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Pierreluo on 2017/9/6.
 */
public class DateTest {
    public static void main(String[] args) {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        long timeNow = date.getTime();
        long time = 30 * 24 * 60 * 60 * 1000;
        System.out.println("now is " + new Date(timeNow));
        System.out.println("after 30 is " + new Date(timeNow + time));
        System.out.println(time);
        long day =  24 * 60 * 60 * 1000;
        long dayNum = 30;
        time = day * dayNum;
        long timeFutrue = timeNow + time;
        /*cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, 30);
        System.out.println(timeNow);
        System.out.println(time);
        System.out.println(timeFutrue);
        //System.out.println(cal.getTime());
        System.out.println(new Date(timeNow));
        System.out.println(new Date(timeNow + time));
        //System.out.println(timeNow);
        //System.out.println(System.currentTimeMillis());
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);*/
    }
}
