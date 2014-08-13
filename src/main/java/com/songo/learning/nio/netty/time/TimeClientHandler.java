/**
 * 
 */
package com.songo.learning.nio.netty.time;

import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;

/**
 * <p>decription:</p>
 * <p>date:2014年8月11日 上午11:04:22</p>
 * @author gsu·napoleon
 */
public class TimeClientHandler implements ChannelInboundHandler {

	private ByteBuf buf;
	
	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelHandler#handlerAdded(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		buf = ctx.alloc().buffer(8);
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelHandler#handlerRemoved(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		buf.release();
		buf = null;
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandler#channelRegistered(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {

	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandler#channelUnregistered(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {

	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandler#channelActive(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

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
		ByteBuf m = (ByteBuf) msg; // (1)
		buf.writeBytes(m);
		m.release();
        try {
        	if (buf.readableBytes() >= 4) {
	            long currentTimeMillis = (buf.readUnsignedInt() - 2208988800L) * 1000L;
	            System.out.println(new Date(currentTimeMillis));
	            ctx.close();
        	}
        } finally {
           
        }
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandler#channelReadComplete(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("Channel Read Complete");
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
		cause.printStackTrace();
		ctx.close();
	}

}
