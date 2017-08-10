/**
 * Package: com.lopframework.lop.servlet.context
 * Description: 
 */
package com.lopframework.lop.servlet.context;

import java.util.Map;

import org.springframework.context.ApplicationContext;

import com.lopframework.lop.service.HandlerMethod;

/**
 * Description:  Lop上下文环境
 * Date: 2017年5月22日 下午5:29:15
 * @author wufenyun 
 */
public interface LopContext {
	
    Map<String,HandlerMethod> getHandlers();
	
	/**
	 * 注册handler
	 */
	void registHandlers();
	
	void setApplicationContext(ApplicationContext applicationContext);
}
