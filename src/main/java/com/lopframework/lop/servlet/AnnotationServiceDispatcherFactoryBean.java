/**
 * 
 */
package com.lopframework.lop.servlet;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 *
 */
@Service
public class AnnotationServiceDispatcherFactoryBean implements FactoryBean<AnnotationServiceDispatcher>,ApplicationContextAware {

	private ApplicationContext applicationContext;
	
	public AnnotationServiceDispatcher getObject() throws Exception {
		return null;
	}

	public Class<?> getObjectType() {
		return null;
	}

	public boolean isSingleton() {
		return false;
	}
	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

}
