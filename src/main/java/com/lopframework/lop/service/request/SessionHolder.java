/**
 * 
 */
package com.lopframework.lop.service.request;

import com.lopframework.lop.service.request.Session;

/**
 * @Description: 
 * @author wufenyun
 * @date 2017年8月13日 下午10:15:26
 */

public class SessionHolder {
	
	private static ThreadLocal<Session> sessionLocal = new ThreadLocal<Session>();
	
	public static Session getSession() {
		return sessionLocal.get();
	}
	
	public static void setSession(Session session) {
		sessionLocal.set(session);
	}
	
	public void remove() {
		sessionLocal.remove();
	}
}
