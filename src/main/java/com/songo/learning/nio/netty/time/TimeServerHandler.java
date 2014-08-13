/**
 * 
 */
package com.songo.learning.nio.netty.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;

/**
 * <p>decription:</p>
 * <p>date:2014年8月11日 上午10:37:16</p>
 * @author gsu·napoleon
 */
public class TimeServerHandler implements ChannelInboundHandler {

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelHandler#handlerAdded(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelHandler#handlerRemoved(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandler#channelRegistered(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandler#channelUnregistered(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandler#channelActive(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		writingTimeServer(ctx);
	}
	
	private void writingTimeServer(final ChannelHandlerContext ctx) {
		final ByteBuf time = ctx.alloc().buffer(8);
		time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
		final ChannelFuture channelFuture = ctx.writeAndFlush(time);
		channelFuture.addListener(new ChannelFutureListener() {
			
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				assert channelFuture == future;
				ctx.close();
			}
		});
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandler#channelInactive(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {

	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandler#channelRead(io.netty.channel.ChannelHandlerContext, java.lang.Object)
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandler#channelReadComplete(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandler#userEventTriggered(io.netty.channel.ChannelHandlerContext, java.lang.Object)
	 */
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
			throws Exception {

	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandler#channelWritabilityChanged(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx)
			throws Exception {

	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandler#exceptionCaught(io.netty.channel.ChannelHandlerContext, java.lang.Throwable)
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.fillInStackTrace();
		ctx.close();
	}

}
