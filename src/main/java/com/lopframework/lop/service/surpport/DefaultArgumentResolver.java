/**
 * 
 */
package com.lopframework.lop.service.surpport;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * @Description: 
 * @author wufenyun
 * @date 2017年8月13日 下午10:59:23
 */

public class DefaultArgumentResolver implements ArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// TODO Auto-generated method stub
		return false;
	}

    @Override
    public Object resolveArgument(MethodParameter parameter, NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }


}
