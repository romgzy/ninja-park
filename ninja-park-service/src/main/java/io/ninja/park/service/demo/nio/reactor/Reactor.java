/**
 * 
 */
package io.ninja.park.service.demo.nio.reactor;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author romgzy
 *
 */
public class Reactor implements Runnable {
	final Logger logger = LoggerFactory.getLogger(Reactor.class);
	//复用
	final Selector selector; 
	// 支持异步操作，对应于java.net.ServerSocket这个类，提供了TCP协议IO接口，支持OP_ACCEPT操作
	final ServerSocketChannel srvSocketChannel;

	Reactor(int port) throws IOException {
		// 获得一个通道管理器
		selector = Selector.open();
		// 获得一个ServerSocket通道
		srvSocketChannel = ServerSocketChannel.open();
		InetSocketAddress address = new InetSocketAddress(
				InetAddress.getLocalHost(), port);
		// 将该通道对应的ServerSocket绑定到port端口
		srvSocketChannel.socket().bind(address);
		// 设置通道为非阻塞
		srvSocketChannel.configureBlocking(false);
		// 将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件,注册该事件后，
		// 当该事件到达时，selector.select()会返回，如果该事件没到达selector.select()会一直阻塞。
		SelectionKey sk = srvSocketChannel.register(selector,
				SelectionKey.OP_ACCEPT);
		logger.debug("-->Start serverSocket.register!");
		// 利用sk的attache功能绑定Acceptor 如果有事情，触发Acceptor
		sk.attach(new Acceptor());
		logger.debug("-->attach(new Acceptor()!");
	}

	public void run() { // normally in a new Thread
		try {
			while (!Thread.interrupted()) {
				selector.select();
				Set selected = selector.selectedKeys();
				Iterator it = selected.iterator();// Selector如果发现channel有OP_ACCEPT或READ事件发生，下列遍历就会进行。
				while (it.hasNext())
					// 来一个事件 第一次触发一个accepter线程// 以后触发SocketReadHandler
					dispatch((SelectionKey) (it.next()));
				selected.clear();
			}
		} catch (IOException ex) {
			// logger.debug("reactor stop!"+ex);}
		}
	}

	// 运行Acceptor或SocketReadHandler
	void dispatch(SelectionKey k) {
		Runnable r = (Runnable) (k.attachment());
		if (r != null) {
			r.run();
		}
	}

	class Acceptor implements Runnable {
		public void run(){
	        try {
	            logger.debug("-->ready for accept!");
	            SocketChannel c = srvSocketChannel.accept();
	            if (c != null)//调用Handler来处理channel　　　　　　　　
	                new SocketReadHandler(selector, c);
	            }catch(IOException ex){
	            	logger.debug("accept stop!", ex);
	            }
	      }
	}
}
