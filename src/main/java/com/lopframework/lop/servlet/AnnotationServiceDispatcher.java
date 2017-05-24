/**
 * Package: com.lopframework.lop.servlet
 * Description: 
 */
package com.lopframework.lop.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.lopframework.lop.servlet.context.ConstantParamName;
import com.lopframework.lop.servlet.context.LopContext;
import com.lopframework.lop.servlet.context.RequestContext;

/**
 * Description:  
 * Date: 2017年5月22日 下午5:09:15
 * @author wufenyun 
 */
@Service
public class AnnotationServiceDispatcher implements ServiceDispatcher {
	
	private LopContext lopContext;
	
	@Override
	public void initStrategies() {
		
	}

	@Override
	public void doDispatch(HttpServletRequest req, HttpServletResponse resp) {
		//1.buildRequestContext
		buildRequestContext(req,resp);
		//2.gethandler
		//3.doService
	}
	
	/** 
	 * Description:  构建请求context
	 * @param req
	 * @param resp
	 * @return
	 */
	private RequestContext buildRequestContext(HttpServletRequest req, HttpServletResponse resp) {
		RequestContext requestContext = new RequestContext();
		requestContext.setAppkey(req.getParameter(ConstantParamName.APPKEY));
		requestContext.setMethod(req.getParameter(ConstantParamName.MEHTOD));
		requestContext.setSign(req.getParameter(ConstantParamName.SIGN));
		requestContext.setAccessToken(req.getParameter(ConstantParamName.TIMESTAMP));
		requestContext.setTimestamp(req.getParameter(ConstantParamName.TIMESTAMP));
		requestContext.setVersion(req.getParameter(ConstantParamName.VERSION));
		return requestContext;
	}

}
