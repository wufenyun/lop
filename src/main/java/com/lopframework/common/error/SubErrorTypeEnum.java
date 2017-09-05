/**
 * Package: com.lopframework.lop.service.response
 * Description: 
 */
package com.lopframework.common.error;

/**
 * Description:  
 * Date: 2017年9月4日 下午8:36:04
 * @author wufenyun 
 */
public enum SubErrorTypeEnum {
    UNKNOWN_ERROR("isp.unknown-error","An unknown error occurred in the service execution"),
    SERVER_RESOURCE_OVERLOAD("isp.server-error","The server resource overload"),
    SERVICE_INTERRUPTED_ERROR("isp.service-inrerrupted-error","The service inrerrupted"),
    SERVICE_TIMEOUT_ERROR("isp.timeout-error","The service handle timeout."),
    INSUFFICIENT_ISV_PERMISSIONS("",""),
    INSUFFICIENT_USER_PERMISSIONS("",""),
    UPLOAD_FAIL("",""),
    INVALID_ENCODING("",""),
    METHOD_OBSOLETED("",""),
    BUSINESS_LOGIC_ERROR("",""),
    MISSING_APP_KEY("",""),
    MISSING_ACCESS_TOKEN("",""),
    INVALID_ACCESS_TOKEN("",""),
    MISSING_TIMESTAMP("",""),
    INVALID_APP_KEY("",""),
    MISSING_SIGNATURE("",""),
    INVALID_SIGNATURE("",""),
    MISSING_API("isv.api-not-exist","The api not exist"),
    INVALID_API("",""),
    MISSING_VERSION("",""),
    INVALID_VERSION("",""),
    UNSUPPORTED_VERSION("",""),
    INVALID_FORMAT("",""),
    MISSING_REQUIRED_ARGUMENTS("",""),
    INVALID_ARGUMENTS("",""),
    EXCEED_USER_INVOKE_LIMITED("",""),
    EXCEED_SESSION_INVOKE_LIMITED("",""),
    EXCEED_APP_INVOKE_LIMITED("",""),
    EXCEED_APP_INVOKE_FREQUENCY_LIMITED("","");
    
    public String code;
    public String msg;
    
    SubErrorTypeEnum(String code,String msg) {
        this.code = code;
        this.msg = msg;
    }
}
