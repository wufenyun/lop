/**
 * 
 */
package com.lopframework.service.controller;

import com.lopframework.common.error.ServiceError;
import com.lopframework.service.handler.PreprocessingHandler;
import com.lopframework.service.request.Request;

/**
 * @Description: 
 * @author wufenyun
 * @date 2017年8月16日 下午10:59:06
 */
public class ParamValidater implements PreprocessingHandler {

	@Override
	public ServiceError proccess(Request request) {
		
		if(request == null) {
			//return new ServiceError(null, "The base request can not be null");
		}
		
		if(request.getAppkey() == null) {
			//return ServiceError.createGenericError(null, "The base request can not be null");
		}
		return null;
	}

}
