/**
 * 
 */
package com.songo.learning.nio.netty.nettyinaction.chapter9;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * <p>decription:</p>
 * <p>date:2014年8月12日 下午12:06:49</p>
 * @author gsu·napoleon
 */
public class BootstrapClient {

	public void bootstrap() {
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(new NioEventLoopGroup())
			.channel(NioSocketChannel.class)
			.handler(new SimpleChannelInboundHandler<ByteBuf>() {

				@Override
				protected void channelRead0(ChannelHandlerContext ctx,
						ByteBuf msg) throws Exception {
					System.out.println("Received Data");
					msg.clear();
				}
			});
		ChannelFuture future = bootstrap.bind("127.0.0.1", 8080);
		future.addListener(new ChannelFutureListener() {
			
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				if (future.isSuccess()) {
					System.out.println("Connection Established");
				} else {
					System.out.println("Connection attempt failed");
					future.cause().printStackTrace();
				}
			}
		});
	}
	
}
