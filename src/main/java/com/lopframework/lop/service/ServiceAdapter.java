/**
 * Package: com.lopframework.lop.service
 * Description: 
 */
package com.lopframework.lop.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;

import com.lopframework.lop.service.request.Session;
import com.lopframework.lop.service.surpport.ArgumentResolver;
import com.lopframework.lop.service.surpport.DefaultArgumentResolver;
import com.lopframework.lop.servlet.context.SessionHolder;

/**
 * Description:  
 * Date: 2017年8月9日 上午11:09:35
 * @author wufenyun 
 */
public class ServiceAdapter {
    
    private ArgumentResolver argumentResolver = new DefaultArgumentResolver();
    
    private final static Logger logger = LoggerFactory.getLogger(ServiceAdapter.class);
    
    public Object invokeHandlerMethod(HandlerMethod handler,HttpServletRequest req, HttpServletResponse resp) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Session session = SessionHolder.getSession();
        System.out.println(session.getBaseRequest().getMethod());
        MethodParameter[] params = handler.getMethodParameters();
        Object[] args = resolveArgument(params,req);
        return handler.getMethod().invoke(handler.getBean(),args);
    }
    
    private Object[] resolveArgument(MethodParameter[] params,HttpServletRequest req) {
        List<Object> list = new ArrayList<>();
        for(MethodParameter param:params) {
            Class<?> clazz = param.getParameterType();
            logger.debug("解析服务方法参数：{}",clazz);
            //argumentResolver.resolveArgument(argumentResolver,req,null);
            Object obj = BeanUtils.instantiate(clazz);
            list.add(obj);
        }
        return list.toArray();
    }
    
    public ArgumentResolver getArgumentResolver() {
        return argumentResolver;
    }

    public void setArgumentResolver(ArgumentResolver argumentResolver) {
        this.argumentResolver = argumentResolver;
    }
}
