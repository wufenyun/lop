/**
 * Package: com.lopframework.lop.service.response
 * Description: 
 */
package com.lopframework.lop.service.response;

/**
 * Description: 异常返回对象 
 * Date: 2017年8月19日 下午5:35:05
 * @author wufenyun 
 */
public class ErrorResponse extends Response {
    /*
     * {"error_response":{"code":50,"msg":"Remote service error"
     * ,"sub_code":"isv.invalid-parameter","sub_msg":"非法参数"}}
     */
    /**
     * 错误码
     */
    private String code;
    /**
     * 错误信息 
     */
    private String msg;
    /**
     * 子错误码
     */
    private String sub_code;
    /**
     * 子错误信息
     */
    private String sub_msg;
    
    public String getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
    public String getSub_code() {
        return sub_code;
    }
    public String getSub_msg() {
        return sub_msg;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public void setSub_code(String sub_code) {
        this.sub_code = sub_code;
    }
    public void setSub_msg(String sub_msg) {
        this.sub_msg = sub_msg;
    }
}
