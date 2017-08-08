/**
 * Package: com.lopframework.lop.service.handler
 * Description: 
 */
package com.lopframework.lop.service.handler;

import java.util.LinkedList;

import com.lopframework.lop.error.LopError;
import com.lopframework.lop.servlet.context.RequestContext;

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
    
    LopError handle(RequestContext context);
}
