/**
 * Package: com.lopframework.lop.servlet.context
 * Description: 
 */
package com.lopframework.lop.servlet.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lopframework.lop.service.request.BaseRequest;
import com.lopframework.lop.service.request.Request;
import com.lopframework.lop.service.request.Session;
import com.lopframework.lop.service.request.SimpleSession;

/**
 * Description:  
 * Date: 2017年8月8日 下午3:14:57
 * @author wufenyun 
 */
public class RequestBuilder {
    
    /** 
     * Description:  构建请求context
     * @param req
     * @param resp
     * @return
     */
    public static Request buildBaseRequest(HttpServletRequest req, HttpServletResponse resp) {
        BaseRequest request = new BaseRequest();
        request.setMethod(req.getParameter(ConstantParamName.MEHTOD));
        request.setVersion(req.getParameter(ConstantParamName.VERSION));
        request.setAppkey(req.getParameter(ConstantParamName.APPKEY));
        request.setSign(req.getParameter(ConstantParamName.SIGN));
        request.setAccessToken(req.getParameter(ConstantParamName.TIMESTAMP));
        request.setTimestamp(req.getParameter(ConstantParamName.TIMESTAMP));
        return request;
    }
    
    public static SimpleSession buildSimpleSession(HttpServletRequest req) {
        SimpleSession session = new SimpleSession();
        session.setHttpServletRequest(req);
        return session;
    }
}
