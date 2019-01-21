package io.ninja.park.service.demo.netty4;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

/**
 * Netty Client Handler
 * 
 * @author <a href="mailto:bluedavy@gmail.com">bluedavy</a>
 */
public class NettyClientHandler extends ChannelDuplexHandler {

	private static final Log LOGGER = LogFactory.getLog(NettyClientHandler.class);

	public NettyClientHandler() {

	}

	public void setClient(NettyClient client) {

	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("msg=>" + msg);
	}

	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		ctx.writeAndFlush(msg, promise);
	}
	
	 private final ChannelFutureListener trafficGenerator = new ChannelFutureListener() {
	        @Override
	        public void operationComplete(ChannelFuture future) {
	            if (future.isSuccess()) {
	            	System.out.println("msg=>ok");
	            } else {
	                future.cause().printStackTrace();
	                future.channel().close();
	            }
	        }
	    };

}
