/**
 * Package: com.lopframework.lop.servlet
 * Description: 
 */
package com.lopframework.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
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
import com.lopframework.common.ResponseFormat;
import com.lopframework.common.error.BaseErrorTypeEnum;
import com.lopframework.common.error.ServiceError;
import com.lopframework.common.error.SubErrorTypeEnum;
import com.lopframework.common.util.AssertUtil;
import com.lopframework.config.FrameConfig;
import com.lopframework.service.ServiceAdapter;
import com.lopframework.service.handler.HandlerChain;
import com.lopframework.service.request.Request;
import com.lopframework.service.request.RequestBuilder;
import com.lopframework.service.request.SessionHolder;
import com.lopframework.service.request.SimpleSession;
import com.lopframework.service.response.Response;
import com.lopframework.service.response.ResponseBuilder;
import com.lopframework.service.surpport.ServiceMapper;
import com.lopframework.servlet.context.DefaultLopContext;
import com.lopframework.servlet.context.LopContext;

/**
 * Description: Date: 2017年5月22日 下午5:09:15
 * 
 * @author wufenyun
 */
public final class AnnotationServiceDispatcher extends WebApplicationObjectSupport
        implements ServiceDispatcher, InitializingBean, DisposableBean {

    private final static Logger logger = LoggerFactory.getLogger(AnnotationServiceDispatcher.class);

    /**
     * lop环境上下文，亦即lop的运行时容器
     */
    private final LopContext lopContext = new DefaultLopContext();
    /**
     * api服务适配器
     */
    private ServiceAdapter serviceAdapter = new ServiceAdapter();
    /**
     * 线程池，使用者可以自己配置相关参数，具体使用请参考文档或示例
     */
    private ThreadPoolExecutor executor;
    /**
     * api服务映射器，当http请求到达时将请求映射到相应的服务
     */
    private ServiceMapper serviceMapper;
    /**
     * 框架级配置信息
     */
    private FrameConfig frameConfig;

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("start to startup lop framework");
        long startTime = System.currentTimeMillis();
        try {
            startUp();
            logger.info("Lop initialization success");
        } catch (Exception e) {
            logger.error("Lop initialization failed", e);
        } finally {
            long elapsedTime = System.currentTimeMillis() - startTime;
            logger.info("Lop startup completed in {} ms", elapsedTime);
        }
    }

    @Override
    public synchronized void startUp() {
        AssertUtil.notNull(getApplicationContext(), "spring applicationContext can not be null");
        lopContext.setApplicationContext(getApplicationContext());
        lopContext.registChannelHandlers();
        lopContext.registHandlerMethods();
        serviceMapper = new ServiceMapper(lopContext);
    }

    @Override
    public void doDispatch(HttpServletRequest req, HttpServletResponse resp) {
        ServiceTask task = new ServiceTask(req, resp);
        Future<?> future = executor.submit(task);
        try {
            if (!future.isDone()) {
                future.get(frameConfig.getTimeoutLimitSeconds(), TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            logger.error("服务执行中断", e);
            Response result = ResponseBuilder.buildErrorResponse(BaseErrorTypeEnum.SERVICE_UNAVAILABLE,
                    SubErrorTypeEnum.SERVICE_INTERRUPTED_ERROR);
            handleResponse(result, resp);
        } catch (ExecutionException e) {
            logger.error("服务执行出错", e);
            Response result = ResponseBuilder.buildErrorResponse(BaseErrorTypeEnum.SERVICE_UNAVAILABLE,
                    SubErrorTypeEnum.SERVICE_TIMEOUT_ERROR);
            handleResponse(result, resp);
        } catch (TimeoutException e) {
            logger.error("服务执行超时", e);
            Response result = ResponseBuilder.buildErrorResponse(BaseErrorTypeEnum.SERVICE_UNAVAILABLE,
                    SubErrorTypeEnum.SERVICE_TIMEOUT_ERROR);
            handleResponse(result, resp);
        } catch (RejectedExecutionException e) {
            logger.error("超出服务器资源限制，无法提供服务", e);
            Response result = ResponseBuilder.buildErrorResponse(BaseErrorTypeEnum.SERVICE_UNAVAILABLE,
                    SubErrorTypeEnum.SERVER_RESOURCE_OVERLOAD);
            handleResponse(result, resp);
        } finally {
            future.cancel(true);
        }
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
            callService(req, resp);
        }

        /**
         * Description: 执行请求任务
         * 
         * @param req
         * @param resp
         */
        private void callService(HttpServletRequest req, HttpServletResponse resp) {
            try {
                // 构建包含系统参数的基础请求对象
                Request lopRequest = RequestBuilder.buildBaseRequest(req, resp);

                // 构建session
                SimpleSession session = RequestBuilder.buildSimpleSession(req);
                session.setRequest(lopRequest);
                SessionHolder.setSession(session);

                // 执行一系列系统操作
                HandlerChain handlerChain = lopContext.getHandlerChain();
                ServiceError error = handlerChain.handle(lopRequest);
                if (null != error) {
                    Response result = ResponseBuilder.buildErrorResponse(BaseErrorTypeEnum.NOT_EXIST_SERVICE,
                            SubErrorTypeEnum.MISSING_API);
                    handleResponse(result, resp);
                    return;
                }

                // Determine handler for the current request.
                HandlerMethod mappedHandler = serviceMapper.getHandlerMethod(lopRequest);
                if (null == mappedHandler) {
                    Response result = ResponseBuilder.buildErrorResponse(BaseErrorTypeEnum.NOT_EXIST_SERVICE,
                            SubErrorTypeEnum.MISSING_API);
                    handleResponse(result, resp);
                } else {
                    Object resultData = serviceAdapter.invokeHandlerMethod(mappedHandler, req, resp);
                    Response result = ResponseBuilder.buildCorrectResponse(lopRequest, resultData);
                    handleResponse(result, resp);
                }
            } catch (Exception ex) {
                logger.error("调用服务出现异常", ex);
                Response result = ResponseBuilder.buildErrorResponse(BaseErrorTypeEnum.SERVICE_UNAVAILABLE,
                        SubErrorTypeEnum.SERVER_RESOURCE_OVERLOAD);
                handleResponse(result, resp);
            } finally {
                SessionHolder.remove();
            }
        }
    }

    /**
     * Description: 处理返回结果,默认使用json序列化
     * 
     * @param result
     * @param response
     */
    private void handleResponse(Object result, HttpServletResponse response) {
        // 将实体对象转换为JSON Object转换
        Object responseJSONObject = JSON.toJSON(result);
        response.setContentType(ResponseFormat.JSON.mimetype);
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(responseJSONObject.toString());
            logger.debug("return result:\n{}", responseJSONObject.toString());
        } catch (IOException e) {
            logger.error("处理返回结果出现IO异常", e);
        } finally {
            if (out != null) {
                out.flush();
                out.close();
                logger.debug("关闭请求响应");
            }
        }
    }

    @Override
    public void shutDown() {
        executor.shutdown();
    }

    @Override
    public void destroy() throws Exception {

    }

    public void setExecutor(ThreadPoolExecutor executor) {
        this.executor = executor;
    }

    public void setFrameConfig(FrameConfig frameConfig) {
        this.frameConfig = frameConfig;
    }

}
