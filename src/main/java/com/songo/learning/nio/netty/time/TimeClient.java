/**
 * 
 */
package com.songo.learning.nio.netty.time;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * <p>decription:</p>
 * <p>date:2014年8月11日 上午10:46:33</p>
 * @author gsu·napoleon
 */
public class TimeClient {

	public static void main(String[] args) {
		String hosts = "127.0.0.1";
		int port = 8080;
		EventLoopGroup workGroup = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(workGroup)
				.channel(NioSocketChannel.class)
				.option(ChannelOption.SO_KEEPALIVE, true)
				.handler(new ChannelInitializer<SocketChannel>() {

					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline().addLast(new TimeMessageDecoder(), new TimeClientHandler());
					}
				});
			ChannelFuture future = bootstrap.connect(hosts, port).sync();
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			workGroup.shutdownGracefully();
		}
	}
	
}
