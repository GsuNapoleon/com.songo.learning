/**
 * 
 */
package com.songo.learning.nio.netty.nettyinaction.chapter8;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.stream.ChunkedStream;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.io.File;
import java.io.FileInputStream;

/**
 * <p>decription:</p>
 * <p>date:2014年8月11日 下午5:44:53</p>
 * @author gsu·napoleon
 */
public class ChunkedWriteHandlerInitializer extends ChannelInitializer<Channel> {
	
	private File file;
	
	public ChunkedWriteHandlerInitializer(File file) {
		this.file = file;
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInitializer#initChannel(io.netty.channel.Channel)
	 */
	@Override
	protected void initChannel(Channel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast(new ChunkedWriteHandler());
		pipeline.addLast(new WriteStreamHandler());
	}

	public final class WriteStreamHandler extends ChannelInboundHandlerAdapter {

		/* (non-Javadoc)
		 * @see io.netty.channel.ChannelInboundHandlerAdapter#channelActive(io.netty.channel.ChannelHandlerContext)
		 */
		@Override
		public void channelActive(ChannelHandlerContext ctx) throws Exception {
			super.channelActive(ctx);
			ctx.writeAndFlush(new ChunkedStream(new FileInputStream(file)));
		}
		
	}
}
