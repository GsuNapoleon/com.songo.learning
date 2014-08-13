/**
 * 
 */
package com.songo.learning.nio.netty.nettyinaction.chapter8;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * <p>decription:</p>
 * <p>date:2014年8月12日 上午11:13:36</p>
 * @author gsu·napoleon
 */
public class HttpDecoderEncoderInitializer extends ChannelInitializer<Channel> {
	
	private boolean client;
	
	public HttpDecoderEncoderInitializer(boolean client) {
		this.client = client;
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInitializer#initChannel(io.netty.channel.Channel)
	 */
	@Override
	protected void initChannel(Channel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		if (client) {
			pipeline.addLast("decoder", new HttpResponseDecoder());
			pipeline.addLast("encoder", new HttpRequestEncoder());
		} else {
			pipeline.addLast("decoder", new HttpRequestDecoder());
			pipeline.addLast("encoder", new HttpResponseEncoder());
		}
	}

}
