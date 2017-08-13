/**
 * 
 */
package com.lopframework.lop.servlet.context;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.method.HandlerMethod;

import com.lopframework.lop.annotation.ServiceProcessor;
import com.lopframework.lop.annotation.ServiceMapping;

/**
 * Description:  
 * Date: 2017年8月10日 上午11:26:30
 * @author wufenyun 
 */
public class DefaultLopContext implements LopContext {
	
	private final Map<String,HandlerMethod> handlerMethods = new HashMap<>();
	
	private ApplicationContext applicationContext;
	
	public final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public Map<String,HandlerMethod> getHandlers() {
		return this.handlerMethods;
	}
	
	@Override
	public void registHandlers() {
		logger.info("start to regist handlers");
		Map<String,Object> handlersMap = applicationContext.getBeansWithAnnotation(ServiceProcessor.class);
		if(null == handlersMap) {
			return;
		}
		
		for(Map.Entry<String, Object> entry:handlersMap.entrySet()) {
		    final Object handler = entry.getValue();
		    ReflectionUtils.doWithMethods(handler.getClass(), new ReflectionUtils.MethodCallback() {
                @Override
                public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
                    ReflectionUtils.makeAccessible(method);
                    ServiceMapping serviceMethodAnno = AnnotationUtils.findAnnotation(method,ServiceMapping.class);
                    String key = serviceMethodAnno.method()+"@" + serviceMethodAnno.version();
                    HandlerMethod handlerMethod = new HandlerMethod(handler,method);
                    handlerMethods.put(key, handlerMethod);
                    logger.info("regist method handler:{}",key);
                }
            }, new ReflectionUtils.MethodFilter() {
                @Override
                public boolean matches(Method method) {
                    return null != AnnotationUtils.findAnnotation(method,ServiceMapping.class);
                }
            });
			
		}
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

}
