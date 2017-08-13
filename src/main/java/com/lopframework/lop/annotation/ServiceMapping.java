/**
 * 
 */
package com.lopframework.lop.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Administrator
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceMapping {
	/**
	 * api
	 */
	String method();
	
	/**
	 * api版本号
	 */
	String version();
}
