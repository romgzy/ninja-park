/**
 * 
 */
package io.ninja.park.service.demo.java.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * @author romgzy
 *
 */
public class LockSupportMain {
	public static void main(String[] args) {
		LockSupport.unpark(Thread.currentThread());;
		System.out.println("begin park!");
		LockSupport.park();
		System.out.println("end park!");
	}

}
