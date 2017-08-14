/**
 * 
 */
package com.lopframework.lop.exception;


/**
 * @Description: 
 * @author wufenyun
 * @date 2017年8月14日 下午10:40:17
 */
public class NoApiFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 3738535743588532948L;

	public NoApiFoundException(String method, String version) {
		super("No service found for api:" + method + version);
	}
}
