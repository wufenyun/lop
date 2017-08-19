/**
 * Package: com.lopframework.lop.servlet
 * Description: 
 */
package com.lopframework.lop.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
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

import com.alibaba.fastjson.JSON;
import com.lopframework.lop.error.LopError;
import com.lopframework.lop.service.ServiceAdapter;
import com.lopframework.lop.service.handler.HandlerChain;
import com.lopframework.lop.service.request.Request;
import com.lopframework.lop.service.request.RequestBuilder;
import com.lopframework.lop.service.request.SessionHolder;
import com.lopframework.lop.service.request.SimpleSession;
import com.lopframework.lop.service.surpport.ServiceMapper;
import com.lopframework.lop.servlet.context.DefaultLopContext;
import com.lopframework.lop.servlet.context.LopContext;
import com.lopframework.lop.util.AssertUtil;

/**
 * Description:  
 * Date: 2017年5月22日 下午5:09:15
 * @author wufenyun 
 */
public class AnnotationServiceDispatcher extends WebApplicationObjectSupport implements ServiceDispatcher, InitializingBean, DisposableBean {
    
    private final static Logger logger = LoggerFactory.getLogger(AnnotationServiceDispatcher.class);
    
    /**
     * lop环境上下文，亦即lop的运行时容器 
     */
    private final LopContext lopContext = new DefaultLopContext();
    
    /**
     * 任务线程池构建类 
     */
    private final ThreadpoolBuilder threadpoolBuilder = new ThreadpoolBuilder();
    /**
     * 线程池，使用者可以自己配置相关参数，具体使用请参考文档或示例
     */
    private ThreadPoolExecutor executor;
    
    /**
     * api服务映射器，当http请求到达时将请求映射到相应的服务 
     */
    private ServiceMapper serviceMapper;
    
    /**
     * api服务适配器
     */
    private ServiceAdapter serviceAdapter = new ServiceAdapter();
    
    
    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("start to startup lop framework");
        long startTime = System.currentTimeMillis();
        try {
            startUp();
            logger.info("Lop initialization success");
        } catch (Exception e) {
            logger.error("Lop initialization failed",e);
        } finally {
            long elapsedTime = System.currentTimeMillis() - startTime;
            logger.info("Lop startup completed in {} ms",elapsedTime);
        }
        
    }

    @Override
    public synchronized void startUp() {
        AssertUtil.notNull(getApplicationContext(), "spring applicationContext can not be null");
        executor = threadpoolBuilder.buildExecutor();
        lopContext.setApplicationContext(getApplicationContext());
        lopContext.registHandlerMethods();
        lopContext.registChannelHandlers();
        serviceMapper = new ServiceMapper(lopContext);
    }
    
    @Override
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
            try {
            	//构建包含系统参数的基础请求对象
                Request lopRequest = RequestBuilder.buildBaseRequest(req, resp);
                //构建session
                SimpleSession session = RequestBuilder.buildSimpleSession(req);
                session.setRequest(lopRequest);
                SessionHolder.setSession(session);
                //执行一系列系统操作
                HandlerChain handlerChain = lopContext.getHandlerChain();
                LopError error = handlerChain.handle(lopRequest);
                if(null != error) {
                    return;
                }
                try {
                	// Determine handler for the current request.
                    HandlerMethod mappedHandler = serviceMapper.getHandlerMethod(lopRequest);
                    Object response = serviceAdapter.invokeHandlerMethod(mappedHandler,req,resp);
                    handleResponse(response, resp);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            } catch (Exception ex) {
                triggerAfterCompletion(processedRequest, response, mappedHandler, ex);
            }
            catch (Error err) {
                triggerAfterCompletionWithError(processedRequest, response, mappedHandler, err);
            } finally {
            	
            }
        }
    }

    private void handleResponse(Object result,HttpServletResponse response) {
    	//将实体对象转换为JSON Object转换  
        Object responseJSONObject = JSON.toJSON(result);
        response.setCharacterEncoding("UTF-8");  
        response.setContentType("application/json; charset=utf-8");  
        PrintWriter out = null;  
        try {  
            out = response.getWriter();  
            out.append(responseJSONObject.toString());  
            logger.debug("return result:\n{}",responseJSONObject.toString());  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (out != null) {  
                out.close();  
            }  
        }  
    }
    
    @Override
    public void shutDown() {
        
    }


   
}
