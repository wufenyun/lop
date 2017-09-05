/**
 * Package: com.lopframework.lop.service.response
 * Description: 
 */
package com.lopframework.common.error;

/**
 * Description:  
 * Date: 2017年9月4日 下午8:03:47
 * @author wufenyun 
 */
public enum BaseErrorTypeEnum {
    
    UNAUTHORIZED(401,"The requested resource requires authentication."),
    FORBIDDEN(403,"The server refuses to fulfill the request."),
    NOT_EXIST_SERVICE(404,"The requested api does not exist."),
    SERVICE_UNAVAILABLE(503,"The server is temporarily unavailable, usually due to high load or maintenance.");
    
    public int code;
    public String msg;
    
    BaseErrorTypeEnum(int code,String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    
}
