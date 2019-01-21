/**
 * Copyright (C) 2015 google, Inc. All Rights Reserved.
 */
package io.ninja.park.service.demo.java.concurrent.produce;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author romgzy
 *
 */
public class Storage {
    private final int MAXSIZE = 100;
    private Lock lock = new ReentrantLock();
    private LinkedList data = new LinkedList();

    private Condition full = lock.newCondition();
    private Condition empty = lock.newCondition();

    public void produce(int num) {
        lock.lock();
        try {
            while (data.size() + num > MAXSIZE) {
                System.out.println("【要生产的产品数量】:" + num + "/t【库存量】:" + data.size()  
                        + "/t暂时不能执行生产任务!");  
                try {
                    full.await();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            for(int i =0 ; i < num ;i++){
                Object o = new Object();
                data.add(o);
            }
            System.out.println("【已经生产产品数】:" + num + "/t【现仓储量为】:" + data.size());  
            //
            empty.signalAll();
        } finally {
            lock.unlock();
        }

    }
    
    public void consume(int num){
        lock.lock();
        try{
            while(data.size() < num){
                System.out.println("【要消费的产品数量】:" + num + "/t【库存量】:" + data.size()  
                        + "/t暂时不能执行生产任务!");  
                try {
                    empty.await();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            for(int i =0 ; i < num ;i++){
                data.remove();
            }
            System.out.println("【已经消费产品数】:" + num + "/t【现仓储量为】:" + data.size()); 
            full.signalAll();
        }finally{
            lock.unlock();
        }
    }
}
