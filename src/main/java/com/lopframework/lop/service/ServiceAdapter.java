/**
 * Package: com.lopframework.lop.service
 * Description: 
 */
package com.lopframework.lop.service;

import java.lang.reflect.InvocationTargetException;

/**
 * Description:  
 * Date: 2017年8月9日 上午11:09:35
 * @author wufenyun 
 */
public class ServiceAdapter {
    
    public Object invokeHandlerMethod(HandlerMethod handler) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        return handler.getMethod().invoke(handler.getHandler());
    }
}
