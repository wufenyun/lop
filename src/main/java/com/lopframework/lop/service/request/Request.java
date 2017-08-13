/**
 * 
 */
package com.lopframework.lop.service.request;


/**
 * @Description: 
 * @author wufenyun
 * @date 2017年8月13日 下午9:58:41
 */

public interface Request {
	
	public String getMethod();

	public String getVersion();

	public String getSign();

	public String getAppkey();

	public String getAccessToken();

	public String getTimestamp();
}
