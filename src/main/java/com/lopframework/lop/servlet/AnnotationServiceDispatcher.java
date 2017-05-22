/**
 * Package: com.lopframework.lop.servlet
 * Description: 
 */
package com.lopframework.lop.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lopframework.lop.servlet.context.LopContext;

/**
 * Description:  
 * Date: 2017年5月22日 下午5:09:15
 * @author wufenyun 
 */
public class AnnotationServiceDispatcher implements ServiceDispatcher {
	
	private LopContext lopContext;
	
	@Override
	public void initStrategies() {
		
	}

	@Override
	public void doDispatch(HttpServletRequest req, HttpServletResponse resp) {
		
	}

}
