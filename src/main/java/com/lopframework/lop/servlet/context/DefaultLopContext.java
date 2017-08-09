/**
 * 
 */
package com.lopframework.lop.servlet.context;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.lopframework.lop.annotation.ServiceProcessor;
import com.lopframework.lop.servlet.AnnotationServiceDispatcher;

/**
 * @author Administrator
 *
 */
public class DefaultLopContext implements LopContext,ApplicationContextAware {
	
	private final Map<String,Object> handlerMethods = new HashMap<String, Object>();
	
	private ApplicationContext applicationContext;
	
	public final static Logger logger = LoggerFactory.getLogger(AnnotationServiceDispatcher.class);
	
	public Set<?> getHandlers() {
		return null;
	}

	public void registHandlers() {
		logger.info("start to regist handlers");
		Map<String,Object> handlersMap = applicationContext.getBeansWithAnnotation(ServiceProcessor.class);
		if(null == handlersMap) {
			return;
		}
		for(Map.Entry<String, Object> entry:handlersMap.entrySet()) {
			Object obj = entry.getValue();
		}
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

}
