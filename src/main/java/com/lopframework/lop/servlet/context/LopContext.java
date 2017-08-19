/**
 * Package: com.lopframework.lop.servlet.context
 * Description: 
 */
package com.lopframework.lop.servlet.context;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.web.method.HandlerMethod;

import com.lopframework.lop.service.handler.HandlerChain;


/**
 * Description:  Lop上下文环境
 * Date: 2017年5月22日 下午5:29:15
 * @author wufenyun 
 */
public interface LopContext {
	
    Map<String,HandlerMethod> getHandlers();
	
	/**
	 * 注册api handler
	 */
	void registHandlerMethods();
	
	void registChannelHandlers();
	
	HandlerChain getHandlerChain();
	
	void setApplicationContext(ApplicationContext applicationContext);
}
