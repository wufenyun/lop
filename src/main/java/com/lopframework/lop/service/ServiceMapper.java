/**
 * Package: com.lopframework.lop.service
 * Description: 
 */
package com.lopframework.lop.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lopframework.lop.servlet.context.LopContext;
import com.lopframework.lop.servlet.context.RequestContext;

/**
 * Description:  
 * Date: 2017年8月9日 下午12:35:56
 * @author wufenyun 
 */
public class ServiceMapper {
    
    private final static Logger logger = LoggerFactory.getLogger(ServiceMapper.class);
    
    private LopContext lopContext;
    
    public ServiceMapper(LopContext lopContext){
        this.lopContext = lopContext;
    }
    
    public HandlerMethod getHandlerMethod(RequestContext requestContext) {
        String api = requestContext.getMethod() + "@" + requestContext.getVersion();
        Map<String, HandlerMethod> handlers = lopContext.getHandlers();
        HandlerMethod handler = handlers.get(api);
        if(null == handler) {
            logger.debug("mapping {} to service failed",api);
            throw new RuntimeException();
        } else {
            logger.debug("mapping {} to service success",api);
            return handler;
        }
    }
    
}
