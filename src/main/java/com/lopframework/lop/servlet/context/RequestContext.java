/**
 * Package: com.lopframework.lop.servlet.context
 * Description: 
 */
package com.lopframework.lop.servlet.context;

import java.util.Map;

/**
 * Description:  http请求上下文
 * Date: 2017年5月24日 上午11:23:52
 * @author wufenyun 
 */
public class RequestContext {
	
	private String method;
	private String version;
	private String sign;
	private String appkey;
	private String token;
	private String accessToken;
	private String timestamp;
	
	private Map<String,String> params;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}
