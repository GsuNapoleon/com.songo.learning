/**
 * 
 */
package com.songo.learning.nio.netty.nettyinaction.chapter4;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

/**
 * <p>decription:</p>
 * <p>date:2014年8月11日 下午4:07:57</p>
 * @author gsu·napoleon
 */
public class NettyNioServer {

	public void run(int port) throws Exception {
		final ByteBuf buf = Unpooled.unreleasableBuffer(
				Unpooled.copiedBuffer("SlamDunk\r\n", CharsetUtil.UTF_8));
		EventLoopGroup lookGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(new NioEventLoopGroup(), new NioEventLoopGroup());
			bootstrap.channel(NioServerSocketChannel.class);
			bootstrap.localAddress(port);
			bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new ChannelInboundHandler() {
						
						@Override
						public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
							
						}
						
						@Override
						public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
							
						}
						
						@Override
						public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
								throws Exception {
							
						}
						
						@Override
						public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
								throws Exception {
							
						}
						
						@Override
						public void channelWritabilityChanged(ChannelHandlerContext ctx)
								throws Exception {
							
						}
						
						@Override
						public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
							
						}
						
						@Override
						public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
							
						}
						
						@Override
						public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
							
						}
						
						@Override
						public void channelRead(ChannelHandlerContext ctx, Object msg)
								throws Exception {
							
						}
						
						@Override
						public void channelInactive(ChannelHandlerContext ctx) throws Exception {
							
						}
						
						@Override
						public void channelActive(ChannelHandlerContext ctx) throws Exception {
							ctx.write(buf.duplicate()).addListener(ChannelFutureListener.CLOSE);
						}
					});
				}
			});
			ChannelFuture future = bootstrap.bind().sync();
			future.channel().closeFuture().sync();
		} finally {
			lookGroup.shutdownGracefully().sync();
		}
	}
	
}
