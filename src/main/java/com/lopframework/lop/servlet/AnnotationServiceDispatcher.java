/**
 * Package: com.lopframework.lop.servlet
 * Description: 
 */
package com.lopframework.lop.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.lopframework.lop.servlet.context.LopContext;

/**
 * Description:  
 * Date: 2017年5月22日 下午5:09:15
 * @author wufenyun 
 */
public class AnnotationServiceDispatcher implements ServiceDispatcher,InitializingBean {
	
	protected final static Logger logger = LoggerFactory.getLogger(AnnotationServiceDispatcher.class);
	
	private LopContext lopContext;
	
	public void afterPropertiesSet() throws Exception {
		logger.info("beanFactory init finished,now do afterPropertiesSet");
		startUp();
	}

	public void startUp() {
		lopContext.registHandlers();
		logger.info("Lop started");
	}

	public void doDispatch(HttpServletRequest req, HttpServletResponse resp) {
		
	}

}
