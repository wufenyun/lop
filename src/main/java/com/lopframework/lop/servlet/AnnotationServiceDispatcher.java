/**
 * Package: com.lopframework.lop.servlet
 * Description: 
 */
package com.lopframework.lop.servlet;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.lopframework.lop.error.LopError;
import com.lopframework.lop.service.HandlerMethod;
import com.lopframework.lop.service.ServiceAdapter;
import com.lopframework.lop.service.ServiceMapper;
import com.lopframework.lop.service.handler.HandlerChain;
import com.lopframework.lop.servlet.context.DefaultLopContext;
import com.lopframework.lop.servlet.context.LopContext;
import com.lopframework.lop.servlet.context.RequestContext;
import com.lopframework.lop.servlet.context.RequestContextBuilder;

/**
 * Description:  
 * Date: 2017年5月22日 下午5:09:15
 * @author wufenyun 
 */
public class AnnotationServiceDispatcher implements ServiceDispatcher,ApplicationContextAware, InitializingBean, DisposableBean {
    
    private final static Logger logger = LoggerFactory.getLogger(AnnotationServiceDispatcher.class);
    
    private LopContext lopContext = new DefaultLopContext();
    
    private HandlerChain handlerChain;
    
    private ThreadPoolExecutor executor;
    
    private ServiceMapper serviceMapper;
    
    private ServiceAdapter serviceAdapter = new ServiceAdapter();
    
    private ApplicationContext applicationContext;
    
    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("beanFactory init finished,now do afterPropertiesSet");
        startUp();
    }

    public void startUp() {
        executor = new ThreadPoolExecutor(100, 200, 5, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());
        serviceMapper = new ServiceMapper(applicationContext);
        serviceMapper.registHandlers();
        logger.info("Lop started");
    }
    
    public void doDispatch(HttpServletRequest req, HttpServletResponse resp) {
        ServiceTask task = new ServiceTask(req, resp);
        executor.submit(task);
    }
    
    @Override
    public void destroy() throws Exception {
        
    }
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    
    public ApplicationContext getApplicationContext() throws BeansException {
        return this.applicationContext;
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
            callService(req,resp);
        }
        
        /** 
         * Description: 执行请求任务 
         * @param req
         * @param resp
         */
        private void callService(HttpServletRequest req, HttpServletResponse resp) {
            //构建请求上下文
            RequestContext requestContext = RequestContextBuilder.buildRequestContext(req, resp);
            //执行一系列系统操作
            LopError error = handlerChain.handle(requestContext);
            if(null == error) {
                return;
            }
            try {
                HandlerMethod handler = serviceMapper.getHandlerMethod(requestContext);
                Object response = serviceAdapter.invokeHandlerMethod(handler);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void shutDown() {
        // TODO Auto-generated method stub
        
    }



    

   
}
