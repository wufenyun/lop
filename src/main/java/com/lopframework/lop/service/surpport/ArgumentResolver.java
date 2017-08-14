package com.lopframework.lop.service.surpport;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;

/**
* @Description: 
* @author wufenyun
* @date 2017年8月13日 下午10:55:52
*/
public interface ArgumentResolver {
	
	boolean supportsParameter(MethodParameter parameter);

	Object resolveArgument(MethodParameter parameter,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception;
}
