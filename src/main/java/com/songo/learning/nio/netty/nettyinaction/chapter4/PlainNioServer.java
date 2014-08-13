/**
 * 
 */
package com.songo.learning.nio.netty.nettyinaction.chapter4;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * <p>decription:</p>
 * <p>date:2014年8月11日 下午4:37:39</p>
 * @author gsu·napoleon
 */
public class PlainNioServer {

	public void run(int port) throws IOException {
		
		ServerSocketChannel socketChannel = ServerSocketChannel.open();
		ServerSocket socket = socketChannel.socket();
		InetSocketAddress socketAddress = new InetSocketAddress(port);
		socket.bind(socketAddress);
		socketChannel.configureBlocking(false);
		Selector selector = Selector.open();
		socketChannel.register(selector, SelectionKey.OP_ACCEPT);
		final ByteBuffer buffer = ByteBuffer.wrap("SlamDunk\r\n".getBytes("UTF-8"));
		
		while (true) {
			selector.select();
			
			runBySelectionKey(selector, buffer);
		}
		
	}
	
	private void runBySelectionKey(Selector selector, final ByteBuffer buffer) throws IOException {
		Set<SelectionKey> readyKeys = selector.selectedKeys();
		Iterator<SelectionKey> keyIterator = readyKeys.iterator();
		while (keyIterator.hasNext()) {
			SelectionKey selectionKey = keyIterator.next();
			keyIterator.remove();
			try {
				acceptable(selector, selectionKey, buffer);
				
				writable(selectionKey);
			} finally {
				selectionKey.cancel();
				selectionKey.channel().close();
			}
		}
	}
	
	private void acceptable(Selector selector, SelectionKey selectionKey, ByteBuffer buffer) throws IOException {
		if (selectionKey.isAcceptable()) {
			ServerSocketChannel serverChannel = (ServerSocketChannel) selectionKey.channel();
			SocketChannel client = serverChannel.accept();
			System.out.println("Accepted connection from " + client);
			serverChannel.configureBlocking(false);
			serverChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE, buffer.duplicate());
		}
	}
	
	private void writable(SelectionKey selectionKey) throws IOException {
		if (selectionKey.isWritable()) {
			SocketChannel client = (SocketChannel) selectionKey.channel();
			ByteBuffer buff = (ByteBuffer) selectionKey.attachment();
			while (buff.hasRemaining()) {
				if (client.write(buff) == 0) {
					break;
				}
			}
			client.close();
		}
	}
	
}
