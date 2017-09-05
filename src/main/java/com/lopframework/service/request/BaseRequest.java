/**
 * Package: com.lopframework.lop.servlet.context
 * Description: 
 */
package com.lopframework.service.request;

import java.util.Locale;
import java.util.Map;

import org.springframework.web.method.HandlerMethod;

import com.lopframework.common.ResponseFormat;

/**
 * Description:  基础请求类，包含了系统级参数
 * Date: 2017年5月24日 上午11:23:52
 * @author wufenyun 
 */
public class BaseRequest implements Request {
	
	private String method;
	private String version;
	private String sign;
	private String appkey;
	private String token;
	private String accessToken;
	private String timestamp;
	private String requestId;
	private Locale locale;
	private String clientIp;
	private ResponseFormat responseFormat;
	/**
	 * 请求参数 
	 */
	private Map<String,String> allParams;
	private Map<String,String> ignoreSignParams;
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

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public ResponseFormat getResponseFormat() {
        return responseFormat;
    }

    public void setResponseFormat(ResponseFormat responseFormat) {
        this.responseFormat = responseFormat;
    }

    public Map<String,String> getAllParams() {
        return allParams;
    }

    public void setAllParams(Map<String,String> allParams) {
        this.allParams = allParams;
    }

    public Map<String,String> getIgnoreSignParams() {
        return ignoreSignParams;
    }

    public void setIgnoreSignParams(Map<String,String> ignoreSignParams) {
        this.ignoreSignParams = ignoreSignParams;
    }
}
