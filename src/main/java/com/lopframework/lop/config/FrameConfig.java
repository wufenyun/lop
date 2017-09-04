/**
 * Package: com.lopframework.lop.config
 * Description: 
 */
package com.lopframework.lop.config;

/**
 * Description:  
 * Date: 2017年9月4日 下午4:56:48
 * @author wufenyun 
 */
public class FrameConfig {
    
    public final static long DEFAULT_TIMEOUT = 3*60;
    
    private long timeoutLimitSeconds;

    public long getTimeoutLimitSeconds() {
        return timeoutLimitSeconds;
    }

    public void setTimeoutLimitSeconds(long timeoutLimitSeconds) {
        this.timeoutLimitSeconds = timeoutLimitSeconds;
    }

}
