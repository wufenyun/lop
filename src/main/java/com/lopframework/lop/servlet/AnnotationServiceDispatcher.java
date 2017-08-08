/**
 * Package: com.lopframework.lop.servlet
 * Description: 
 */
package com.lopframework.lop.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.lopframework.lop.error.LopError;
import com.lopframework.lop.service.handler.ExecutionHandlerChain;
import com.lopframework.lop.service.handler.HandlerChain;
import com.lopframework.lop.servlet.context.LopContext;
import com.lopframework.lop.servlet.context.RequestContext;
import com.lopframework.lop.servlet.context.RequestContextBuilder;

/**
 * Description:  
 * Date: 2017年5月22日 下午5:09:15
 * @author wufenyun 
 */
public class AnnotationServiceDispatcher implements ServiceDispatcher,InitializingBean {
    
    protected final static Logger logger = LoggerFactory.getLogger(AnnotationServiceDispatcher.class);
    
    private LopContext lopContext;
    
    private HandlerChain handlerChain;
    
    public void afterPropertiesSet() throws Exception {
        logger.info("beanFactory init finished,now do afterPropertiesSet");
        startUp();
    }

    public void startUp() {
        lopContext.registHandlers();
        logger.info("Lop started");
    }
    
    private void initStrategyes() {
        handlerChain = new ExecutionHandlerChain();
    }

    public void doDispatch(HttpServletRequest req, HttpServletResponse resp) {
        RequestContextBuilder
    }

    private class ServiceTask implements Runnable {
        
        private HttpServletRequest req;
        private HttpServletResponse resp;
        
        public ServiceTask(HttpServletRequest req, HttpServletResponse resp) {
            this.req = req;
            this.resp = resp;
        }
        
        @Override
        public void run() {
            doService(req,resp);
        }
        
        /** 
         * Description: 执行请求任务 
         * @param req
         * @param resp
         */
        private void doService(HttpServletRequest req, HttpServletResponse resp) {
            //构建请求上下文
            RequestContext context = RequestContextBuilder.buildRequestContext(req, resp);
            //执行一系列系统操作
            LopError error = handlerChain.handle(context);
            if(null == error) {
                return;
            }
            
        }
    }
}
