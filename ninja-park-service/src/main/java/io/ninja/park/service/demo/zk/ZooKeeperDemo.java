/**
 * Copyright (C) 2015 google, Inc. All Rights Reserved.
 */
package io.ninja.park.service.demo.zk;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author haochong.z
 * 
 */
public class ZooKeeperDemo {
    private static final String host = "localhost";
    private static final int port = 2118;
    private static final Logger log = LoggerFactory.getLogger(ZooKeeperDemo.class);
    private static final String zkPath = "/nileader";

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            ZooKeeper zk = new ZooKeeper(host, port, new Watcher() {
                public void process(WatchedEvent event) {
                    sessionEvent(countDownLatch, event);
                }
            });
           // List<ACL> acl = new ArrayList<ACL>();
           // acl.add(new ACL(ZooDefs.Perms.ALL,Ids.ANYONE_ID_UNSAFE));
           // acl.add(new ACL(ZooDefs.Perms.READ, Ids.AUTH_IDS));
            try {
                if (zk.exists(zkPath, false) == null) {
                    zk.create(zkPath, null, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                } else {
                    log.info("节点存在");
                }
               //zk.delete("/google/google", -1);
            } catch (KeeperException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            zk.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private static void sessionEvent(CountDownLatch connectionLatch, WatchedEvent event) {
        if (event.getState() == KeeperState.SyncConnected) {
            log.info("收到ZK连接成功事件！");
            connectionLatch.countDown();
        } else if (event.getState() == KeeperState.Expired) {
            log.error("会话超时，等待重新建立ZK连接...");
            try {

            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        } // Disconnected：Zookeeper会自动处理Disconnected状态重连
        else if (event.getState() == KeeperState.Disconnected) {
            log.info("tb_hj_schedule Disconnected，等待重新建立ZK连接...");
            try {

            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        } else if (event.getState() == KeeperState.NoSyncConnected) {
            log.info("tb_hj_schedule NoSyncConnected，等待重新建立ZK连接...");
            try {

            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        } else {
            log.info("tb_hj_schedule 会话有其他状态的值，event.getState() =" + event.getState() + ", event  value="
                    + event.toString());
        }
    }

}
