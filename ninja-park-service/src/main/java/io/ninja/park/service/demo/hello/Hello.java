/**
 * Copyright (C) 2016 google, Inc. All Rights Reserved.
 */
package io.ninja.park.service.demo.hello;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author romgzy
 *
 */
public class Hello {
    public static String FORMAT2 = "yyyy-MM-dd HH:mm:ss";

    /**
     * @param args
     */
    public static void main(String[] args) {
        Date d1 = new Date(1461945600*1000L);
        SimpleDateFormat sdf2 = new SimpleDateFormat(FORMAT2);// 转换格式
        System.out.println(sdf2.format(d1));
        System.out.println(System.getProperty("java.version"));
    }

}
