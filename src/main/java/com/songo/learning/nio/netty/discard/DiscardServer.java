/**
 * 
 */
package com.songo.learning.nio.netty.discard;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * <p>decription:</p>
 * <p>date:2014年8月8日 下午5:04:42</p>
 * @author gsu·napoleon
 */
public class DiscardServer {

	private int port;
	
	public DiscardServer() {}
	
	public DiscardServer(int port) {
		this.port = port;
	}
	
	public void run() throws Exception {
		
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap
				.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<Channel>() {
					@Override
					protected void initChannel(Channel ch) throws Exception {
						ch.pipeline().addLast(new DiscardServerHandler());
					}
				})
				.option(ChannelOption.SO_BACKLOG, 128)
				.childOption(ChannelOption.SO_KEEPALIVE, true);
			
			ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
			channelFuture.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
		
	}
	
	public static void main(String[] args) {
		int scanPort = 8080;
		if (args.length > 0) {
			scanPort = Integer.valueOf(args[1]);
		} 
		DiscardServer server = new DiscardServer(scanPort);
		try {
			server.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
