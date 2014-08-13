/**
 * 
 */
package com.songo.learning.nio.netty.nettyinaction.chapter9;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

/**
 * <p>decription:</p>
 * <p>date:2014年8月12日 下午12:13:27</p>
 * @author gsu·napoleon
 */
public class BootstrapClientWithOptionsAndAttrs {

	public void bootstrap() {
		final AttributeKey<Integer> id = AttributeKey.valueOf("ID");
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(new NioEventLoopGroup())
			.channel(NioSocketChannel.class)
			.handler(new SimpleChannelInboundHandler<ByteBuf>() {

				/* (non-Javadoc)
				 * @see io.netty.channel.ChannelInboundHandlerAdapter#channelRegistered(io.netty.channel.ChannelHandlerContext)
				 */
				@Override
				public void channelRegistered(ChannelHandlerContext ctx)
						throws Exception {
					super.channelRegistered(ctx);
					Integer value = ctx.channel().attr(id).get().intValue();
					System.out.println("Received Data is value = " + value);
				}

				@Override
				protected void channelRead0(ChannelHandlerContext ctx,
						ByteBuf msg) throws Exception {
					System.out.println("Received Data");
					msg.clear();
				}
			});
		bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
		bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000);
		
		ChannelFuture future = bootstrap.connect("127.0.0.1", 8080);
		future.syncUninterruptibly();
	}
	
}
