package com.date;

import com.interfaces.RunInterface;
import junit.extensions.RepeatedTest;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Created by Pierreluo on 2017/9/26.
 */
public class Java8Date implements RunInterface {
    public static void main(String[] args) {
        new Java8Date().run();
    }

    /**
     * 时间类 - JAVA8 替代类
     * 官方给出的解释： simple beautiful strong immutable thread - safe
     */
    @Override
    public void run() {
        // 代替Date
        Instant instant = Instant.now(Clock.systemDefaultZone());
        System.out.println(instant);
        // 代替SimpleDataFormat
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //System.out.println(dtf.format(instant)); -> exception
        // 代替Calendar
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(dtf.format(ldt.minusMonths(1L)));

        LocalDate date = LocalDate.now();
        System.out.println(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
        String text = date.format(formatter);
        System.out.println(text);
        LocalDate parsedDate = LocalDate.parse(text, formatter);


    }
}
