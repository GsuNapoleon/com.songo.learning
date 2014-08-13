/**
 * 
 */
package com.songo.learning.nio.netty.nettyinaction.chapter11;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

/**
 * <p>decription:</p>
 * <p>date:2014年8月12日 下午3:39:40</p>
 * @author gsu·napoleon
 */
public class TextWebSocketFrameHandler extends
		SimpleChannelInboundHandler<TextWebSocketFrame> {

	private final ChannelGroup group;

    public TextWebSocketFrameHandler(ChannelGroup group) {
        this.group = group;
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt == WebSocketServerProtocolHandler.ServerHandshakeStateEvent.HANDSHAKE_COMPLETE) {

            ctx.pipeline().remove(HttpRequestHandler.class);

            group.writeAndFlush(new TextWebSocketFrame("Client " + ctx.channel() + " joined"));

            group.add(ctx.channel());
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        group.writeAndFlush(msg.retain());
    }

}
