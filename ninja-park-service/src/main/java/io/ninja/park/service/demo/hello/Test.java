/**
 * Copyright (C) 2016 google, Inc. All Rights Reserved.
 */
package io.ninja.park.service.demo.hello;

import java.util.Arrays;

/**
 * @author romgzy
 *
 */
public class Test {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String value = String.format("%d_%d", System.currentTimeMillis(), 1);
        System.out.println(value);
        Integer i = 4;
        System.out.println(Integer.toBinaryString(i)); 
        String[] unionCodes = ",00120170621000002".split(",");
        System.out.println(Arrays.toString(unionCodes)); 
        int j = 1 ,k=2;
        System.out.println((j=k=3));
        System.out.println(j);
        System.out.println(k);
    }

}