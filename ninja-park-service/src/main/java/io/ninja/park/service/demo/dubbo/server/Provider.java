package io.ninja.park.service.demo.dubbo.server;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author romgzy
 *
 */
public class Provider {

    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"/spring-config-service.xml"});
        context.start();
        System.in.read(); // 按任意键退出
       
    }

}
