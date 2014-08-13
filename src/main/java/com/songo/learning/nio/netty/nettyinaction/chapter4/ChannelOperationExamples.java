/**
 * 
 */
package com.songo.learning.nio.netty.nettyinaction.chapter4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.util.CharsetUtil;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * <p>decription:</p>
 * <p>date:2014年8月11日 下午3:43:52</p>
 * @author gsu·napoleon
 */
public class ChannelOperationExamples {

	public static void writingToChannel() {
		Channel channel = null;	// Get the channel reference from somewhere
		ByteBuf buf = Unpooled.copiedBuffer("Sa Ku La Gi · Ha La Mi Qi", CharsetUtil.UTF_8);
		@SuppressWarnings("null")
		final ChannelFuture future = channel.write(buf);
		future.addListener(new ChannelFutureListener() {
			
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				if (future.isSuccess()) {
					System.out.println("Writing To Channel is Successfully!!!");
				} else {
					System.out.println("Writing To Channel is Failed!!!");
					future.cause().printStackTrace();
				}
			}
		});
	}
	
	public static void writingToChannelManyThreads() {
		final Channel channel = null;	// Get the channel reference from somewhere
		final ByteBuf buf = Unpooled.copiedBuffer("Sa Ku La Gi · Ha La Mi Qi", CharsetUtil.UTF_8);
		Runnable task = new Runnable() {
			
			@Override
			@SuppressWarnings("null")
			public void run() {
				channel.write(buf);
			}
		};
		Executor executor = Executors.newCachedThreadPool();
		executor.execute(task);
		executor.execute(task);
	}
	
}
