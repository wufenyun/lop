package com.lopframework.lop.service.surpport;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
* @Description: 
* @author wufenyun
* @date 2017年8月13日 下午10:55:52
*/
public interface ArgumentResolver {
	
	boolean supportsParameter(MethodParameter parameter);

	Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception;
}
