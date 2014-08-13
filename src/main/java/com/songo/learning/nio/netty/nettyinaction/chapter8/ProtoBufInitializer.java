/**
 * 
 */
package com.songo.learning.nio.netty.nettyinaction.chapter8;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;

import com.google.protobuf.MessageLite;

/**
 * <p>decription:</p>
 * <p>date:2014年8月12日 上午11:42:19</p>
 * @author gsu·napoleon
 */
public class ProtoBufInitializer extends ChannelInitializer<Channel> {
	
	private final MessageLite messageLite;
	
	public ProtoBufInitializer(MessageLite messageLite) {
		this.messageLite = messageLite;
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInitializer#initChannel(io.netty.channel.Channel)
	 */
	@Override
	protected void initChannel(Channel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast(new ProtobufVarint32FrameDecoder());
		pipeline.addLast(new ProtobufEncoder());
		pipeline.addLast(new ProtobufDecoder(messageLite));
		pipeline.addLast(new MyProtoBufHandler());
	}
	
	public static final class MyProtoBufHandler extends SimpleChannelInboundHandler<Object> {

		/* (non-Javadoc)
		 * @see io.netty.channel.SimpleChannelInboundHandler#channelRead0(io.netty.channel.ChannelHandlerContext, java.lang.Object)
		 */
		@Override
		protected void channelRead0(ChannelHandlerContext ctx, Object msg)
				throws Exception {
			// to-do
		}
		
	}

}
