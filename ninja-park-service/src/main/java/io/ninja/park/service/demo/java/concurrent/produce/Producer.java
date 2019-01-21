/**
 * Copyright (C) 2015 google, Inc. All Rights Reserved.
 */
package io.ninja.park.service.demo.java.concurrent.produce;

/**
 * 生产者消费者模式--生产者
 * 
 * @author romgzy
 *
 */
public class Producer extends Thread {
    
    private Storage storage;
    private int num;
    
    public Producer (Storage storage){
        this.storage =storage;
    }
    
    @Override
    public void run() {
        storage.produce(num);
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    
    
}
