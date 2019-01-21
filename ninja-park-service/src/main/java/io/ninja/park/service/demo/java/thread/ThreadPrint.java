/**
 * 
 */
package io.ninja.park.service.demo.java.thread;

/**
 * @author romgzy
 *
 */
public class ThreadPrint {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Maj maj = new Maj();
		Thread thread_A = new Thread(new Print(maj, 'A'));
		Thread thread_B = new Thread(new Print(maj, 'B'));

		Thread thread_C = new Thread(new Print(maj, 'C'));
		thread_A.start();
		thread_B.start();
		thread_C.start();
	}

}

class Maj {
	private char ch = 'A';

	public char getCh() {
		return ch;
	}

	public void setCh(char ch) {
		this.ch = ch;
	}
}

class Print implements Runnable {
	private char ch = ' ';
	private char next =' ';
	private Maj maj = null;

	public Print(Maj maj, char ch) {
		this.maj = maj;
		this.ch = ch;
		if (ch == 'C'){
			this.next = 'A';
		}else{
			this.next =  (char) (ch + 1);
		}
	}

	@Override
	public void run() {
		
		int i = 0;
		while (i < 10) {
			 System.out.println("===="+ch +"==next=" + next);
			synchronized (maj) {
				while(maj.getCh() != ch){
					try {
						maj.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				System.out.println(maj.getCh());
				maj.setCh(next);
				i++;
				
				maj.notifyAll();
			}
		}
		// TODO Auto-generated method stub

	}

}
