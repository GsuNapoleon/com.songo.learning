/**
 * 
 */
package com.songo.learning.nio.netty.time;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * <p>decription:</p>
 * <p>date:2014年8月11日 下午12:03:37</p>
 * @author gsu·napoleon
 */
public class TimeMessageDecoder extends ByteToMessageDecoder {

	/* (non-Javadoc)
	 * @see io.netty.handler.codec.ByteToMessageDecoder#decode(io.netty.channel.ChannelHandlerContext, io.netty.buffer.ByteBuf, java.util.List)
	 */
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {
		if (in.readableBytes() < 3) {
			return;
		}
		out.add(in.readBytes(4));
	}

}
