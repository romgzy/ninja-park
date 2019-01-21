/*
 * Copyright 1999-2011 google Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.ninja.park.service.demo.netty4;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * NettyClient.
 * 
 * @author qian.lei
 * @author william.liangf
 */
public class NettyClient {
	static final String HOST = System.getProperty("host", "127.0.0.1");
	static final int PORT = Integer.parseInt(System.getProperty("port", "8023"));
	private static final StringDecoder DECODER = new StringDecoder();
	private static final StringEncoder ENCODER = new StringEncoder();
	private static final Logger logger = LoggerFactory.getLogger(NettyClient.class);

	protected volatile Bootstrap bootstrap;

	private volatile Channel channel; // volatile, please copy reference to use

	protected final int workerCount = 2;

	public NettyClient() {
		try {
			doOpen();
		} catch (Throwable ex) {

		}
	}

	protected void doOpen() throws Throwable {
		bootstrap = createBootstrap();
	}

	protected void doConnect() throws Throwable {
		long start = System.currentTimeMillis();

	}

	protected void doDisConnect() throws Throwable {

	}

	protected void doClose() throws Throwable {

	}

	private Bootstrap createBootstrap() {
		final Bootstrap bootstrap = new Bootstrap();
		EventLoopGroup group = new NioEventLoopGroup();

		bootstrap.group(group);
		bootstrap.channel(NioSocketChannel.class);

		bootstrap.handler(new ServerChannelInitializer());

		bootstrap.validate();

		return bootstrap;
	}

	protected class ServerChannelInitializer extends ChannelInitializer<Channel> {

		protected ServerChannelInitializer() {

		}

		@Override
		protected void initChannel(Channel ch) throws Exception {
			ChannelPipeline pipeline = ch.pipeline();
			// Add the text line codec combination first,

			pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));

			pipeline.addLast(DECODER);

			pipeline.addLast(ENCODER);
			pipeline.addLast("dispatcher", new NettyClientHandler());

		}

		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

		}

	}

	public ChannelFuture write(Channel ch, Object o) throws InterruptedException {
		return ch.writeAndFlush(o);
	}

	public ChannelFuture write(Object o) throws InterruptedException {
		if (channel == null) {
			channel = getConnect();
		}
		return write(channel, o);
	}

	public Channel getConnect() throws InterruptedException {
		channel = bootstrap.connect(HOST, PORT).sync().channel();
		return channel;
	}

	public void close() {
		if (channel == null) {
			channel.close();
		}

		if (bootstrap != null) {
			bootstrap.config().group().shutdownGracefully(0, 10, TimeUnit.SECONDS).awaitUninterruptibly();
			bootstrap = null;
		}
	}

	public static void main(String[] args) throws Exception {
		int a = args.length;
		final NettyClient netty = new NettyClient();
		ChannelFuture f = netty.write("xxxx\r\n");
		
		
	}
}