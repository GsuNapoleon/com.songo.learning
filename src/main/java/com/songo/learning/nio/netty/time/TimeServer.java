/**
 * 
 */
package com.songo.learning.nio.netty.time;

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
 * <p>date:2014年8月11日 上午11:07:39</p>
 * @author gsu·napoleon
 */
public class TimeServer {

	private int port;
	
	public TimeServer() {}
	
	public TimeServer(int port) {
		this.port = port;
	}
	
	public void run() throws Exception {
		EventLoopGroup workLoopGroup = new NioEventLoopGroup();
		EventLoopGroup bossLoopGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossLoopGroup, workLoopGroup)
				.channel(NioServerSocketChannel.class)
				.option(ChannelOption.SO_BACKLOG, 128)
				.childOption(ChannelOption.SO_KEEPALIVE, true)
				.childHandler(new ChannelInitializer<Channel>() {

					@Override
					protected void initChannel(Channel ch) throws Exception {
						ch.pipeline().addLast(new TimeServerHandler());
					}
				});
			ChannelFuture channelFuture = bootstrap.bind(port).sync();
			channelFuture.channel().closeFuture().sync();
		} finally {
			workLoopGroup.shutdownGracefully();
			bossLoopGroup.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) {
		TimeServer server = new TimeServer(8080);
		try {
			server.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
