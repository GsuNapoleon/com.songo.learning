/**
 * 
 */
package com.songo.learning.nio.netty.nettyinaction.chapter8;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

/**
 * <p>decription:</p>
 * <p>date:2014年8月12日 上午11:21:18</p>
 * @author gsu·napoleon
 */
public class HttpsCodecInitializer extends ChannelInitializer<Channel> {
	
	private SSLContext context;
	private boolean client;
	
	public HttpsCodecInitializer(SSLContext context, boolean client) {
		this.context = context;
		this.client = client;
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInitializer#initChannel(io.netty.channel.Channel)
	 */
	@Override
	protected void initChannel(Channel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		SSLEngine engine = context.createSSLEngine();
		engine.setUseClientMode(client);
		pipeline.addLast("ssl", new SslHandler(engine));
		
		if (client) {
			pipeline.addLast("codec", new HttpClientCodec());
		} else {
			pipeline.addLast("codec", new HttpServerCodec());
		}
	}

}
