/**
 * 
 */
package com.songo.learning.nio.netty.nettyinaction.chapter8;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.FileRegion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * <p>decription:</p>
 * <p>date:2014年8月12日 上午10:42:33</p>
 * @author gsu·napoleon
 */
public class DefaultFileRegionUsage {

	public static void transfer(Channel channel, File file) throws FileNotFoundException {
		final FileInputStream fis = new FileInputStream(file);
		FileRegion fr = new DefaultFileRegion(fis.getChannel(), 0, file.length());
		channel.writeAndFlush(fr).addListener(new ChannelFutureListener() {
			
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				if (future.isSuccess()) {
					// do-something
					fis.close();
				}
			}
		});
	}
	
}
