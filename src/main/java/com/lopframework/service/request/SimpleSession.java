/**
 * Package: com.lopframework.lop.service.request
 * Description: 
 */
package com.lopframework.service.request;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description:  
 * Date: 2017年8月14日 下午3:21:11
 * @author wufenyun 
 */
public class SimpleSession implements Session {
    
    private final Map<String,Object> attributes = new HashMap<>();
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private Request request;
    
    @Override
    public HttpServletRequest getHttpServletRequest() {
        return this.httpServletRequest;
    }

    @Override
    public void addAttribute(String name, Object value) {
        attributes.put(name, value);
    }

    @Override
    public Object getAttribute(String name) {
        return attributes.get(name);
    }

    @Override
    public void removeAttribute(String name) {
        attributes.remove(name);
    }

    @Override
    public Request getBaseRequest() {
        return this.request;
    }
    
    public HttpServletResponse getHttpServletResponse() {
        return httpServletResponse;
    }

    public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }


}
