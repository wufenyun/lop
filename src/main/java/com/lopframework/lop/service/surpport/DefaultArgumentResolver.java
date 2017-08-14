/**
 * 
 */
package com.lopframework.lop.service.surpport;

import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import com.lopframework.lop.util.AssertUtil;

/**
 * @Description: 
 * @author wufenyun
 * @date 2017年8月13日 下午10:59:23
 */
public class DefaultArgumentResolver implements ArgumentResolver {
    
    private final static Logger logger = LoggerFactory.getLogger(DefaultArgumentResolver.class);
    
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return false;
	}

    @Override
    public Object resolveArgument(MethodParameter parameter, HttpServletRequest request) {
        AssertUtil.notNull(parameter);
        logger.debug("start to resolve the parameter:{}",parameter.getParameterName());
        Class<?> clazz = parameter.getParameterType();
        Object target = BeanUtils.instantiate(clazz);
        Field[] fields = clazz.getDeclaredFields();
        for(Field field:fields) {
            field.setAccessible(true);
            String filedName = field.getName();
            String value = request.getParameter(filedName);
            if(!StringUtils.isEmpty(value)) {
                String type = field.getType().toString();//得到此属性的类型
                if (type.endsWith("String")) {
                   ReflectionUtils.setField(field, target, value);    //给属性设值
                }else if(type.endsWith("int") || type.endsWith("Integer")){
                   ReflectionUtils.setField(field, target, new Integer(value));       //给属性设值
                }else{
                    logger.debug("无法解析的属性类型：{}",field.getType());
                }
                
            }
        }
        return target;
    }


}
