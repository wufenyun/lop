/**
 * Package: com.lopframework.lop.service
 * Description: 
 */
package com.lopframework.lop.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.lopframework.lop.annotation.ServiceProcessor;
import com.lopframework.lop.servlet.AnnotationServiceDispatcher;
import com.lopframework.lop.servlet.context.LopContext;
import com.lopframework.lop.servlet.context.RequestContext;

/**
 * Description:  
 * Date: 2017年8月9日 下午12:35:56
 * @author wufenyun 
 */
public class ServiceMapper {
    
    private final static Logger logger = LoggerFactory.getLogger(ServiceMapper.class);
    
    private ApplicationContext applicationContext;
    
    public ServiceMapper(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }
    
    public HandlerMethod getHandlerMethod(RequestContext requestContext) {
        return null;
    }
    
    public void registHandlers() {
        logger.info("start to regist handlers");
        Map<String,Object> handlersMap = applicationContext.getBeansWithAnnotation(ServiceProcessor.class);
        if(null == handlersMap) {
            return;
        }
        for(Map.Entry<String, Object> entry:handlersMap.entrySet()) {
            Object obj = entry.getValue();
            logger.info("regist handler:{}",entry.getKey());
        }
    }
}
