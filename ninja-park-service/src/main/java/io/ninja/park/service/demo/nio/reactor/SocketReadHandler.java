/**
 * 
 */
package io.ninja.park.service.demo.nio.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author romgzy
 *
 */
public class SocketReadHandler implements Runnable {
	final Logger logger = LoggerFactory.getLogger(SocketReadHandler.class);
	final int MAXIN = 1024;
	final int MAXOUT = 1024;
	private final SocketChannel socket;
	final SelectionKey sk;
	ByteBuffer input = ByteBuffer.allocate(MAXIN);
	ByteBuffer output = ByteBuffer.allocate(MAXOUT);
	static final int READING = 0, SENDING = 1;
	int state = READING;

	public SocketReadHandler(Selector selector, SocketChannel c)
			throws IOException {
		socket = c;
		socket.configureBlocking(false);
		sk = socket.register(selector, SelectionKey.OP_READ);
		// 将SelectionKey绑定为本Handler
		// 下一步有事件触发时，将调用本类的run方法。//参看dispatch(SelectionKey k)
		sk.attach(this);
		// 同时将SelectionKey标记为可读，以便读取。
		sk.interestOps(SelectionKey.OP_READ);
		selector.wakeup();
	}

	boolean inputIsComplete() {
		return false;
	}

	boolean outputIsComplete() {
		return false;
	}

	void process() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			if (state == READING) {
				read();
			} else if (state == SENDING) {
				send();
			}
		} catch (Exception ex) {
			logger.debug("readRequest error" + ex);
		}

	}

	void read()throws IOException{
		socket.read(input);
		if(inputIsComplete()){
			process();
			state= SENDING;
			sk.interestOps(SelectionKey.OP_WRITE);
		}
	}
	
	void send()throws IOException{
		socket.write(output);
		if(outputIsComplete()){
			sk.cancel();
		}
	}
	/** * 处理读取data * @param key * @throws Exception */
	private void readRequest() throws Exception {
		ByteBuffer input = ByteBuffer.allocate(1024);
		input.clear();
		try {
			int bytesRead = socket.read(input);
			//

			// 激活线程池 处理这些requestrequestHandle(new Request(socket,btt));
			// ..
		} catch (Exception e) {
		}
	}

}
