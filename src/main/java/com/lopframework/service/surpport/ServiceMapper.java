/**
 * Package: com.lopframework.lop.service
 * Description: 
 */
package com.lopframework.service.surpport;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;

import com.lopframework.service.request.Request;
import com.lopframework.servlet.context.LopContext;

/**
 * Description:  
 * Date: 2017年8月9日 下午12:35:56
 * @author wufenyun 
 */
public class ServiceMapper {
    
    private LopContext lopContext;
    
    private final static Logger logger = LoggerFactory.getLogger(ServiceMapper.class);
    
    public ServiceMapper(LopContext lopContext){
        this.lopContext = lopContext;
    }
    
    /** 
     * Description:  获取api处理器
     * @param request
     * @return
     */
    public HandlerMethod getHandlerMethod(Request request) {
        String api = request.getMethod() + "@" + request.getVersion();
        Map<String, HandlerMethod> handlers = lopContext.getHandlers();
        HandlerMethod handler = handlers.get(api);
        if(null == handler) {
            logger.debug("mapping {} to service failed",api);
        } else {
            logger.info("mapping {} to service success",api);
        }
        return handler;
    }
}
