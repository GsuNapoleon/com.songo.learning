/**
 * 
 */
package com.songo.learning.nio.netty.nettyinaction.chapter8;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * <p>decription:</p>
 * <p>date:2014年8月12日 上午10:54:08</p>
 * @author gsu·napoleon
 */
public class HttpAggregatorInitializer extends ChannelInitializer<Channel> {
	
	private boolean client;
	
	public HttpAggregatorInitializer(boolean client) {
		this.client = client;
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInitializer#initChannel(io.netty.channel.Channel)
	 */
	@Override
	protected void initChannel(Channel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		if (client) {
			pipeline.addLast("codec", new HttpClientCodec());
		} else {
			pipeline.addLast("codec", new HttpServerCodec());
		}
		pipeline.addLast("aggegator", new HttpObjectAggregator(512 * 1024));
	}

}
