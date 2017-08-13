/**
 * Package: com.lopframework.lop.service.handler
 * Description: 
 */
package com.lopframework.lop.service.handler;

import java.util.LinkedList;

import com.lopframework.lop.error.LopError;
import com.lopframework.lop.service.request.Request;

/**
 * Description: 需要执行的处理链路
 * Date: 2017年8月8日 下午3:31:25
 * @author wufenyun 
 */
public class ExecutionHandlerChain implements HandlerChain {
    
    /**
     * 系统级的预处理器
     */
    private volatile LinkedList<PreprocessingHandler> systemHandlers;
    
    /**
     * 用户级预处理器 
     */
    private volatile LinkedList<PreprocessingHandler> subscriberHandlers;
    
    @Override
    public HandlerChain addSytemHandlerFirst(PreprocessingHandler handler) {
        if(null == systemHandlers) {
            systemHandlers = new LinkedList<>();
        }
        systemHandlers.addFirst(handler);
        return this;
    }

    @Override
    public HandlerChain addSytemHandlerLast(PreprocessingHandler handler) {
        if(null == systemHandlers) {
            systemHandlers = new LinkedList<>();
        }
        systemHandlers.addLast(handler);
        return this;
    }

    @Override
    public HandlerChain addSubscriberHandlerFirst(PreprocessingHandler handler) {
        if(null == subscriberHandlers) {
            subscriberHandlers = new LinkedList<>();
        }
        subscriberHandlers.addFirst(handler);
        return this;
    }

    @Override
    public HandlerChain addSubscriberHandlerLast(PreprocessingHandler handler) {
        if(null == subscriberHandlers) {
            subscriberHandlers = new LinkedList<>();
        }
        subscriberHandlers.addLast(handler);
        return this;
    }
    
    @Override
    public LopError handle(Request context) {
        LopError error = null;
        //先执行系统级预处理器
        for(PreprocessingHandler systemHandler:systemHandlers) {
            error = systemHandler.proccess(context);
            if(null != error) {
                return error;
            }
        }
        for(PreprocessingHandler subscriberHandler:subscriberHandlers) {
            error = subscriberHandler.proccess(context);
            if(null != error) {
                return error;
            }
        }
        return error;
    }


    public LinkedList<PreprocessingHandler> getSystemHandler() {
        return systemHandlers;
    }

    public void setSystemHandler(LinkedList<PreprocessingHandler> systemHandlers) {
        this.systemHandlers = systemHandlers;
    }

    public LinkedList<PreprocessingHandler> getSubscriberHandler() {
        return subscriberHandlers;
    }

    public void setSubscriberHandler(LinkedList<PreprocessingHandler> subscriberHandlers) {
        this.subscriberHandlers = subscriberHandlers;
    }


}
