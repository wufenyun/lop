/**
 * Package: com.lopframework.lop.servlet
 * Description: 
 */
package com.lopframework.lop.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description:  
 * Date: 2017年5月22日 下午4:20:05
 * @author wufenyun 
 */
public interface ServiceDispatcher {
	
	void startUp();
	
	void doDispatch(HttpServletRequest req, HttpServletResponse resp);
}
