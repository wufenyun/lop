/**
 * 
 */
package com.lopframework.service.request;

import java.util.Locale;
import java.util.Map;

import com.lopframework.common.ResponseFormat;

/**
 * @Description: 
 * @author wufenyun
 * @date 2017年8月13日 下午9:58:41
 */

public interface Request {
    
    String getRequestId();
    
	String getMethod();

	String getVersion();

	String getSign();

	String getAppkey();

	String getAccessToken();

	String getTimestamp();
	
	Locale getLocale();
	
	String getClientIp();
	
	ResponseFormat getResponseFormat();
	
	Map<String,String> getAllParams();
	
	Map<String,String> getIgnoreSignParams();
}
