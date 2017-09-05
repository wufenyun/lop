/**
 * Package: com.lopframework.lop.service.handler
 * Description: 
 */
package com.lopframework.service.handler;

import java.util.LinkedList;

import com.lopframework.common.error.ServiceError;
import com.lopframework.service.request.Request;

/**
 * Description:  
 * Date: 2017年8月8日 下午4:38:43
 * @author wufenyun 
 */
public interface HandlerChain {
    
    HandlerChain addSytemHandlerFirst(PreprocessingHandler handler);
    
    HandlerChain addSytemHandlerLast(PreprocessingHandler handler);
    
    HandlerChain addSubscriberHandlerFirst(PreprocessingHandler handler);
    
    HandlerChain addSubscriberHandlerLast(PreprocessingHandler handler);
    
    LinkedList<PreprocessingHandler> getSystemHandler();
    
    LinkedList<PreprocessingHandler> getSubscriberHandler();
    
    ServiceError handle(Request request);
}
