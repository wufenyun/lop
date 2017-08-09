/**
 * Package: com.lopframework.lop.service.handler
 * Description: 
 */
package com.lopframework.lop.service;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * Description:  
 * Date: 2017年8月8日 下午12:55:18
 * @author wufenyun 
 */
public class HandlerMethod {
    
    /**
     * 处理类
     */
    private Object handler;
    /**
     * 处理方法 
     */
    private Method method;
    /**
     * 不需要注解的属性
     */
    private Set<String> noNeedSignFiledName;
    
    public Object getHandler() {
        return handler;
    }
    public void setHandler(Object handler) {
        this.handler = handler;
    }
    public Method getMethod() {
        return method;
    }
    public void setMethod(Method method) {
        this.method = method;
    }
    public Set<String> getNoNeedSignFiledName() {
        return noNeedSignFiledName;
    }
    public void setNoNeedSignFiledName(Set<String> noNeedSignFiledName) {
        this.noNeedSignFiledName = noNeedSignFiledName;
    }
}
