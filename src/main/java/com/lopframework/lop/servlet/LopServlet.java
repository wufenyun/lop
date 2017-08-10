/**
 * Package: com.lopframework.lop.servlet
 * Description: 
 */

package com.lopframework.lop.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Description: 
 * Date: 2017年5月22日 下午1:42:15
 * 
 * @author wufenyun
 */
public class LopServlet extends HttpServlet {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 3278783794600319609L;
	
	private AnnotationServiceDispatcher annotationServiceDispatcher;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		ApplicationContext context = getApplicationContext(config);
		annotationServiceDispatcher = (AnnotationServiceDispatcher) context.getBean("annotationServiceDispatcher");
		annotationServiceDispatcher.setApplicationContext(context);
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    annotationServiceDispatcher.doDispatch(req, resp);
	}
	
	private ApplicationContext getApplicationContext(ServletConfig config) {
		WebApplicationContext wac =WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		return wac;
	}
}
