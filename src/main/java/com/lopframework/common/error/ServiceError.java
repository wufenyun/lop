/**
 * Package: com.lopframework.lop.error
 * Description: 
 */
package com.lopframework.common.error;

/**
 * Description:  
 * Date: 2017年8月8日 下午5:28:38
 * @author wufenyun 
 */
public class ServiceError {
    
    /**
     * 错误码
     */
    private int code;
    /**
     * 错误信息 
     */
    private String msg;
    /**
     * 子错误码
     */
    private String subCode;
    /**
     * 子错误信息
     */
    private String subMsg;
    
    public ServiceError(int code,String msg) {
        this(code,msg,null,null);
    }
    
    public ServiceError(int code,String msg,String subCode,String subMsg) {
        this.code = code;
        this.msg = msg;
        this.subCode = subCode;
        this.subMsg = subMsg;
    }
    
    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getSubCode() {
        return subCode;
    }
    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }
    public String getSubMsg() {
        return subMsg;
    }
    public void setSubMsg(String subMsg) {
        this.subMsg = subMsg;
    }
}
