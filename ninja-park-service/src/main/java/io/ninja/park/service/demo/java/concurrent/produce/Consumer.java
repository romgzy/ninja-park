/**
 * Copyright (C) 2015 google, Inc. All Rights Reserved.
 */
package io.ninja.park.service.demo.java.concurrent.produce;

/**
 * @author romgzy
 *
 */
public class Consumer extends Thread {
    private int num ;
    private Storage storage;
    public Consumer(Storage storage){
        this.storage = storage;
    }

    @Override
    public void run() {
        storage.consume(num);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    
    
}
