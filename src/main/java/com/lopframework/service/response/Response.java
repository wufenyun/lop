/**
 * Package: com.lopframework.lop.service.response
 * Description: 
 */
package com.lopframework.service.response;

/**
 * Description: 基础返回信息类 
 * Date: 2017年8月9日 上午11:12:56
 * @author wufenyun 
 */
public class Response {
    
    /**
     * 请求id
     */
    private String reqId;
    /**
     * 请求消耗时间，单位秒
     */
    private double duration;
    
    public String getReqId() {
        return reqId;
    }
    public void setReqId(String reqId) {
        this.reqId = reqId;
    }
    public double getDuration() {
        return duration;
    }
    public void setDuration(double duration) {
        this.duration = duration;
    }
    
}
