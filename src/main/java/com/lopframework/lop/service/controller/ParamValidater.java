/**
 * 
 */
package com.lopframework.lop.service.controller;

import com.lopframework.lop.common.error.GenericError;
import com.lopframework.lop.common.error.LopError;
import com.lopframework.lop.service.handler.PreprocessingHandler;
import com.lopframework.lop.service.request.Request;

/**
 * @Description: 
 * @author wufenyun
 * @date 2017年8月16日 下午10:59:06
 */

public class ParamValidater implements PreprocessingHandler {

	@Override
	public LopError proccess(Request request) {
		
		if(request == null) {
			return GenericError.createGenericError(null, "The base request can not be null");
		}
		
		if(request.getAppkey() == null) {
			return GenericError.createGenericError(null, "The base request can not be null");
		}
		return null;
	}

}
