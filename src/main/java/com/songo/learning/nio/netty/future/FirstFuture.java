/**
 * 
 */
package com.songo.learning.nio.netty.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * <p>decription:</p>
 * <p>date:2014年8月8日 下午3:09:59</p>
 * @author gsu·napoleon
 */
public class FirstFuture {

	public void run() {
		Runnable task1 = new Runnable() {
			
			@Override
			public void run() {
				// ......
				System.out.println("A...B...C...D...E......");
			}
		};
		
		Callable<String> task2 = new Callable<String>() {
			
			@Override
			public String call() throws Exception {
				return "Successly";
			}
		};
		
		ExecutorService service = Executors.newCachedThreadPool();
		Future<?> future1 = (Future<?>) service.submit(task1);
		Future<String> future2 = (Future<String>) service.submit(task2);
		if (!future1.isDone() || !future2.isDone()) {
			System.out.println(".................");
		}
	}
	
	public static void main(String[] args) {
		FirstFuture future = new FirstFuture();
		future.run();
	}
	
}
