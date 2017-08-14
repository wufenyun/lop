/**
 * Package: com.lopframework.lop.servlet
 * Description: 
 */
package com.lopframework.lop.servlet;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.web.method.HandlerMethod;

import com.lopframework.lop.service.ServiceAdapter;
import com.lopframework.lop.service.handler.HandlerChain;
import com.lopframework.lop.service.request.Request;
import com.lopframework.lop.service.request.SimpleSession;
import com.lopframework.lop.service.surpport.ServiceMapper;
import com.lopframework.lop.servlet.context.DefaultLopContext;
import com.lopframework.lop.servlet.context.LopContext;
import com.lopframework.lop.servlet.context.RequestBuilder;
import com.lopframework.lop.servlet.context.SessionHolder;

/**
 * Description:  
 * Date: 2017年5月22日 下午5:09:15
 * @author wufenyun 
 */
public class AnnotationServiceDispatcher extends WebApplicationObjectSupport implements ServiceDispatcher, InitializingBean, DisposableBean {
    
    private final static Logger logger = LoggerFactory.getLogger(AnnotationServiceDispatcher.class);
    
    private final LopContext lopContext = new DefaultLopContext();
    
    private HandlerChain handlerChain;
    
    private ThreadPoolExecutor executor;
    
    private ServiceMapper serviceMapper;
    
    private ServiceAdapter serviceAdapter = new ServiceAdapter();
    
    
    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("beanFactory init finished,now do afterPropertiesSet");
        startUp();
    }

    public void startUp() {
        executor = new ThreadPoolExecutor(100, 200, 5, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());
        lopContext.setApplicationContext(getApplicationContext());
        lopContext.registHandlers();
        serviceMapper = new ServiceMapper(lopContext);
        logger.info("Lop started");
    }
    
    public void doDispatch(HttpServletRequest req, HttpServletResponse resp) {
        ServiceTask task = new ServiceTask(req, resp);
        Future<?> future = executor.submit(task);
        try {
            while (!future.isDone()) {
                future.get(1000, TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } catch (ExecutionException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } catch (TimeoutException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
    
    @Override
    public void destroy() throws Exception {
        
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
            //构建包含系统参数的基础请求对象
            Request lopRequest = RequestBuilder.buildBaseRequest(req, resp);
            SimpleSession session = RequestBuilder.buildSimpleSession(req);
            session.setRequest(lopRequest);
            SessionHolder.setSession(session);
            //执行一系列系统操作
//            LopError error = handlerChain.handle(requestContext);
//            if(null == error) {
//                return;
//            }
            try {
                HandlerMethod handler = serviceMapper.getHandlerMethod(lopRequest);
                Object response = serviceAdapter.invokeHandlerMethod(handler,req,resp);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void shutDown() {
        
    }



    

   
}
