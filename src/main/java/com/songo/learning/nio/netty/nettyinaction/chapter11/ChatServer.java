/**
 * 
 */
package com.songo.learning.nio.netty.nettyinaction.chapter11;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.ImmediateEventExecutor;

/**
 * <p>decription:</p>
 * <p>date:2014年8月12日 下午1:58:13</p>
 * @author gsu·napoleon
 */
public class ChatServer {

	private final ChannelGroup group = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
	private final EventLoopGroup lookGroup = new NioEventLoopGroup();
	private Channel channel;
	
	public ChannelFuture start(String inetHost, int inetPort) {
		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.group(lookGroup);
		bootstrap.channel(NioServerSocketChannel.class);
		bootstrap.childHandler(createInitializer(group));
		
		ChannelFuture future = bootstrap.bind(inetHost, inetPort);
		future.syncUninterruptibly();
		channel = future.channel();
		
		return future;
	}
	
	public void destroy() {
		if (channel != null) {
			channel.close();
		}
		group.close();
		lookGroup.shutdownGracefully();
	}

	/**
	 * <p>decription:</p>
	 * <p>date:2014年8月12日 下午2:10:58</p>
	 * @author gsu·napoleon
	 * @param group
	 * @return
	 */
	private ChannelInitializer<Channel> createInitializer(ChannelGroup group) {
		return new ChatServerInitializer(group);
	}
	
	public static void main(String[] args) {
		
		final ChatServer endpoint = new ChatServer();
		ChannelFuture future = endpoint.start("127.0.0.1", 8080);
		
		Runtime.getRuntime().addShutdownHook(new Thread() {

			/* (non-Javadoc)
			 * @see java.lang.Thread#run()
			 */
			@Override
			public void run() {
				endpoint.destroy();
			}
			
		});
		
		future.channel().closeFuture().syncUninterruptibly();
		
	}
	
}
