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
                //得到此属性的类型
                String type = field.getType().toString();
                if (type.endsWith("String")) {
                   //给属性设值
                   ReflectionUtils.setField(field, target, value);
                }else if(type.endsWith("short") || type.endsWith("Short")){
                    ReflectionUtils.setField(field, target, Short.valueOf(value));
                 }else if(type.endsWith("int") || type.endsWith("Integer")){
                   ReflectionUtils.setField(field, target, Integer.valueOf(value));
                }else if(type.endsWith("long") || type.endsWith("Long")){
                    ReflectionUtils.setField(field, target, Long.valueOf(value));       
                }else if(type.endsWith("fload") || type.endsWith("Float")){
                    ReflectionUtils.setField(field, target, Float.valueOf(value));
                }else if(type.endsWith("double") || type.endsWith("Double")){
                    ReflectionUtils.setField(field, target, Double.valueOf(value));
                }else if(type.endsWith("char") || type.endsWith("Character")){
                    ReflectionUtils.setField(field, target, value);
                }else if(type.endsWith("boolean") || type.endsWith("Boolean")){
                    ReflectionUtils.setField(field, target, Boolean.valueOf(value));
                }else{
                    logger.warn("无法解析的属性类型：{}",field.getType());
                }
            }
        }
        return target;
    }


}
