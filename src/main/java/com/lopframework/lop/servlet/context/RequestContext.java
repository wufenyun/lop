/**
 * Package: com.lopframework.lop.servlet.context
 * Description: 
 */
package com.lopframework.lop.servlet.context;

import java.util.Map;

import com.lopframework.lop.service.HandlerMethod;

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
	
	/**
	 * 请求参数 
	 */
	private Map<String,Object> paramMap;
	
	/**
	 * api对应的处理器 
	 */
	private HandlerMethod serviceHandler;
	/**
	 * 服务调用开始时间 
	 */
	private long serviceStartTime;
	/**
	 * 服务调用结束时间
	 */
	private long serviceEndTime;

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

    public Map<String,Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String,Object> paramMap) {
        this.paramMap = paramMap;
    }

    public long getServiceStartTime() {
        return serviceStartTime;
    }

    public void setServiceStartTime(long serviceStartTime) {
        this.serviceStartTime = serviceStartTime;
    }

    public long getServiceEndTime() {
        return serviceEndTime;
    }

    public void setServiceEndTime(long serviceEndTime) {
        this.serviceEndTime = serviceEndTime;
    }

    public HandlerMethod getServiceHandler() {
        return serviceHandler;
    }

    public void setServiceHandler(HandlerMethod serviceHandler) {
        this.serviceHandler = serviceHandler;
    }
}
