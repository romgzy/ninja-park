/**
 * Copyright (C) 2015 google, Inc. All Rights Reserved.
 */
package io.ninja.park.service.demo.java.concurrent.produce;

/**
 * @author romgzy
 *
 */
public class TestProduce {

    /**
     * @param args
     */
    public static void main(String[] args) {
       Storage storage = new Storage();
       Producer p1= new Producer(storage);
       Producer p2= new Producer(storage);
       Consumer c1 = new Consumer(storage);
       Consumer c2 = new Consumer(storage);
       Consumer c3 = new Consumer(storage);
       p1.setNum(20);
       p2.setNum(30);
      
       c1.setNum(10);
       c2.setNum(10);
       c3.setNum(10);
       p1.start();
      
       c1.start();
       p2.start();
       c2.start();
       c3.start();
       System.out.println("===========");
    }

}
