/**
 * 
 */
package com.songo.learning.nio.netty.nettyinaction.chapter8;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.LineBasedFrameDecoder;

/**
 * <p>decription:</p>
 * <p>date:2014年8月11日 下午5:54:52</p>
 * @author gsu·napoleon
 */
public class CmdHandlerInitializer extends ChannelInitializer<Channel> {

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInitializer#initChannel(io.netty.channel.Channel)
	 */
	@Override
	protected void initChannel(Channel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast(new CmdDecorder(65 * 1024));
		pipeline.addLast(new CmdHandler());
	}
	
	public static final class Cmd {
		private final ByteBuf name;
		private final ByteBuf args;
		
		public Cmd(ByteBuf name, ByteBuf args) {
			this.name = name;
			this.args = args;
		}

		/**
		 * @return the name
		 */
		public ByteBuf name() {
			return name;
		}

		/**
		 * @return the args
		 */
		public ByteBuf args() {
			return args;
		}
	}

	public static final class CmdDecorder extends LineBasedFrameDecoder {

		/**
		 * @param maxLength
		 */
		public CmdDecorder(int maxLength) {
			super(maxLength);
		}

		/* (non-Javadoc)
		 * @see io.netty.handler.codec.LineBasedFrameDecoder#decode(io.netty.channel.ChannelHandlerContext, io.netty.buffer.ByteBuf)
		 */
		@Override
		protected Object decode(ChannelHandlerContext ctx, ByteBuf buffer)
				throws Exception {
			ByteBuf frame = (ByteBuf) super.decode(ctx, buffer);
			if (frame == null) {
				return null;
			}
			int index = frame.indexOf(frame.readerIndex(), frame.writerIndex(), (byte)' ');
			return new Cmd(frame.slice(frame.readerIndex(), index), frame.slice(index + 1, frame.writerIndex()));
		}
		
	}
	
	public static final class CmdHandler extends SimpleChannelInboundHandler<Cmd> {

		/* (non-Javadoc)
		 * @see io.netty.channel.SimpleChannelInboundHandler#channelRead0(io.netty.channel.ChannelHandlerContext, java.lang.Object)
		 */
		@Override
		protected void channelRead0(ChannelHandlerContext ctx, Cmd msg)
				throws Exception {
			
		}
		
	}
	
}
