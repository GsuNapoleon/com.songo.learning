/**
 * 
 */
package com.songo.learning.nio.netty.nettyinaction.chapter8;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.ssl.SslHandler;

/**
 * <p>decription:</p>
 * <p>date:2014年8月12日 上午11:48:29</p>
 * @author gsu·napoleon
 */
public class SslChannelInitializer extends ChannelInitializer<Channel> {
	
	private final SSLContext context;
	private final boolean client;
	private final boolean startTls;
	
	public SslChannelInitializer(SSLContext context, boolean client, boolean startTls) {
		this.context = context;
		this.client = client;
		this.startTls = startTls;
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInitializer#initChannel(io.netty.channel.Channel)
	 */
	@Override
	protected void initChannel(Channel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		SSLEngine engine = context.createSSLEngine();
		engine.setUseClientMode(client);
		pipeline.addFirst("ssl", new SslHandler(engine, startTls));
	}

}
