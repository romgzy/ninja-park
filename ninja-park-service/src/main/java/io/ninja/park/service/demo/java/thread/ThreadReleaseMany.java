/**
 * 
 */
package io.ninja.park.service.demo.java.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author romgzy
 *
 */
public class ThreadReleaseMany {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		ReentrantLock lock = new ReentrantLock();
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				lock.lock();
				System.out.println("子线程执行........");
				lock.unlock();
			}
		});

		// 主线程去获得了锁，然后获得了两次，随后t.start()住现场开启了t线程，然后主线程和t线程可以同时执行
		// 但是，t线程执行时尝试去获取锁lock，发现一直被主线程持有，所以一直等待
		// 知道主线程lock.unlock()释放了一次，但是这时候t线程仍不能获得该锁，主线程也还持有锁的，一直未释放

		// ----线程持有锁，锁被主线程锁持有，其他线程就不能获取锁
		lock.lock();
		lock.lock();
		t.start();

		Thread.sleep(100);
		System.out.println("主线程执行........");

		lock.unlock(); // 主线程释放锁，仅仅释放了一次

	}

}
