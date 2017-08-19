/**
 * Package: com.lopframework.lop.service.response
 * Description: 
 */
package com.lopframework.lop.service.response;

/**
 * Description:  
 * Date: 2017年8月19日 下午5:36:55
 * @author wufenyun 
 */
public class CorrectResponse extends Response {
    
    /**
     * 返回结果，即api接口返回的数据 
     */
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
