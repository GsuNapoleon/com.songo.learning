/**
 * 
 */
package com.songo.learning.nio.netty.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;

/**
 * <p>decription:</p>
 * <p>date:2014年8月8日 下午4:56:57</p>
 * @author gsu·napoleon
 */
public class DiscardServerHandler implements ChannelInboundHandler {

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
		lookingReceivedData(msg);
		writingEchoServer(ctx, msg);
	}
	
	private void lookingReceivedData(Object msg) {
		ByteBuf bb = (ByteBuf) msg;
		try {
			while (bb.isReadable()) {
				System.out.println((char)bb.readByte());
				System.out.flush();
			}
		} finally {
			ReferenceCountUtil.release(msg);
		}
	}
	
	private void writingEchoServer(ChannelHandlerContext ctx, Object msg) {
		ctx.write(msg);
		ctx.flush();
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
