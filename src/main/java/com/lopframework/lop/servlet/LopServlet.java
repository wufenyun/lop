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
	
	private ServiceDispatcher serviceDispatcher;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		ApplicationContext context = getApplicationContext(config);
		serviceDispatcher = context.getBean(ServiceDispatcher.class);
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		serviceDispatcher.doDispatch(req, resp);
	}
	
	private ApplicationContext getApplicationContext(ServletConfig config) {
		ApplicationContext wac =WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		System.out.println(wac);
		//Object obj = wac.getBean("userController");
		Object obj2 = wac.getBean("userService");
		ApplicationContext ac = (ApplicationContext)config.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		System.out.println("--------------------");
		System.out.println(ac);
		//Object obj3 = wac.getBean("userController");
		Object obj4 = wac.getBean("userService");
		return wac;
	}
}
