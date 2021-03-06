/**
 * 
 */
package com.lopframework.config.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Service;

/**
 * @author Administrator
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface ServiceProcessor {
	
	/**
	 * value
	 */
	String value() default "";
	
	/**
	 * 版本号
	 */
	String version() default "";
}
