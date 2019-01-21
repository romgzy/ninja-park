/**
 * 
 */
package io.ninja.park.service.demo.java.concurrent;

/**
 * @author romgzy
 *
 */
public class NoVisibility {
	static boolean stop;
	static class ReaderThread extends Thread{
		public void run(){
			while(!stop){
				int a = 0;
				
			}
			System.out.println("exit");
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ReaderThread().start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		stop = true;
		System.out.println("stop = " + stop);
	}

}
