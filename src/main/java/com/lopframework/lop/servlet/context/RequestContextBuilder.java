/**
 * Package: com.lopframework.lop.servlet.context
 * Description: 
 */
package com.lopframework.lop.servlet.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description:  
 * Date: 2017年8月8日 下午3:14:57
 * @author wufenyun 
 */
public class RequestContextBuilder {
    
    /** 
     * Description:  构建请求context
     * @param req
     * @param resp
     * @return
     */
    public static RequestContext buildRequestContext(HttpServletRequest req, HttpServletResponse resp) {
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
