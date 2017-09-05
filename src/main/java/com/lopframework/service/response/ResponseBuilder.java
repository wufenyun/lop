/**
 * Package: com.lopframework.lop.service.response
 * Description: 
 */
package com.lopframework.service.response;

import com.lopframework.common.error.BaseErrorTypeEnum;
import com.lopframework.common.error.SubErrorTypeEnum;
import com.lopframework.service.request.Request;

/**
 * Description:  
 * Date: 2017年8月19日 下午5:54:08
 * @author wufenyun 
 */
public class ResponseBuilder {
    
    /** 
     * Description: 构建正常返回结果 
     * @param lopRequest
     * @param data
     * @return
     */
    public static Response buildCorrectResponse(Request lopRequest,Object data) {
        CorrectResponse response = new CorrectResponse();
        response.setReqId(lopRequest.getRequestId());
        //response.setDuration();
        response.setData(data);
        return response;
    }
    
    /** 
     * Description: 构建异常返回结果 
     * @param lopRequest
     * @param data
     * @return
     */
    public static Response buildErrorResponse(Request lopRequest,BaseErrorTypeEnum base,SubErrorTypeEnum sub) {
        ErrorResponse response = new ErrorResponse(base.code,base.msg,sub.code,sub.msg);
        response.setReqId(lopRequest.getRequestId());
        //response.setDuration();
        return response;
    }
    
    public static Response buildErrorResponse(BaseErrorTypeEnum base,SubErrorTypeEnum sub) {
        ErrorResponse response = new ErrorResponse(base.code,base.msg,sub.code,sub.msg);
        //response.setDuration();
        return response;
    }
    
    public static Response buildBussinessErrorResponse(Request lopRequest,BaseErrorTypeEnum base,SubErrorTypeEnum sub) {
        ErrorResponse response = new ErrorResponse(base.code,base.msg,sub.code,sub.msg);
        response.setReqId(lopRequest.getRequestId());
        //response.setDuration();
        return response;
    }
}
