/**
 * Package: com.lopframework.lop.service.response
 * Description: 
 */
package com.lopframework.lop.service.response;

import com.lopframework.lop.service.request.Request;

/**
 * Description:  
 * Date: 2017年8月19日 下午5:54:08
 * @author wufenyun 
 */
public class ResponseBuilder {
    
    /** 
     * Description:  
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
}
