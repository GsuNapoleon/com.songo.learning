/**
 * 
 */
package com.songo.learning.nio.mina;


/**
 * <p>decription:</p>
 * <p>date:2014年8月13日 上午10:03:07</p>
 * @author gsu·napoleon
 */
public class TimeServerHandler {
//	public class TimeServerHandler extends IoHandlerAdapter {

	/* (non-Javadoc)
	 * @see org.apache.mina.core.service.IoHandlerAdapter#sessionIdle(org.apache.mina.core.session.IoSession, org.apache.mina.core.session.IdleStatus)
	 */
//	@Override
//	public void sessionIdle(IoSession session, IdleStatus status)
//			throws Exception {
//		System.out.println("IDLE : " + session.getIdleCount(status));
//	}

	/* (non-Javadoc)
	 * @see org.apache.mina.core.service.IoHandlerAdapter#exceptionCaught(org.apache.mina.core.session.IoSession, java.lang.Throwable)
	 */
//	@Override
//	public void exceptionCaught(IoSession session, Throwable cause)
//			throws Exception {
//		super.exceptionCaught(session, cause);
//		session.close(true);
//		cause.printStackTrace();
//	}

	/* (non-Javadoc)
	 * @see org.apache.mina.core.service.IoHandlerAdapter#messageReceived(org.apache.mina.core.session.IoSession, java.lang.Object)
	 */
//	@Override
//	public void messageReceived(IoSession session, Object message)
//			throws Exception {
//		String str = message.toString();
//		if ("quit".equals(str)) {
//			session.close(true);
//			return;
//		}
//		Date date = new Date();
//		session.write(date.toString());
//		System.out.println("Mina Writing ......");
//	}

}
