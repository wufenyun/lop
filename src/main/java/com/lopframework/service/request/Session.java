/**
 * 
 */
package com.lopframework.service.request;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 用于管理会话信息
 * @author wufenyun
 * @date 2017年8月13日 下午10:12:32
 */

public interface Session {
	
	HttpServletRequest getHttpServletRequest();
	
	void addAttribute(String name,Object value);
	
	Object getAttribute(String name);
	
	void removeAttribute(String name);
	
	Request getBaseRequest();
}
