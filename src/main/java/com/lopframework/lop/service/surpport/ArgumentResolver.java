package com.lopframework.lop.service.surpport;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;

/**
* @Description: 
* @author wufenyun
* @date 2017年8月13日 下午10:55:52
*/
public interface ArgumentResolver {
	
	boolean supportsParameter(MethodParameter parameter);

	Object resolveArgument(MethodParameter parameter,HttpServletRequest webRequest);
}
