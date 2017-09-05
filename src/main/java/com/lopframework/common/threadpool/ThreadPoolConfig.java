/**
 * Package: com.lopframework.lop.threadpool
 * Description: 
 */
package com.lopframework.common.threadpool;

import java.util.concurrent.TimeUnit;

/**
 * Description:  线程池配置类
 * Date: 2017年8月19日
 * @author wufenyun 
 */
public class ThreadPoolConfig {
    
    public static final int DEFAULT_CORE_POOL_SIZE = 30;
    public static final int DEFAULT_MAX_POOL_SIZE = 120;
    public static final int DEFAULT_KEEP_ALIVE_SECONDS = 60;
    public static final int DEFAULT_QUENE_CAPACITY = 0;
    
    private int corePoolSize;
    private int maximumPoolSize;
    private long keepAliveTime;
    private int queueCapacity;
    private TimeUnit unit = TimeUnit.SECONDS;
    
    public ThreadPoolConfig() {
        
    }
    
    public ThreadPoolConfig(int corePoolSize, int maximumPoolSize, long keepAliveTime,int queueCapacity) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime;
        this.queueCapacity = queueCapacity;
    }
    
    public int getCorePoolSize() {
        return corePoolSize;
    }
    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }
    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }
    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }
    public long getKeepAliveTime() {
        return keepAliveTime;
    }
    public void setKeepAliveTime(long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }
    public TimeUnit getUnit() {
        return unit;
    }
    public int getQueueCapacity() {
        return queueCapacity;
    }
    public void setQueueCapacity(int queueCapacity) {
        this.queueCapacity = queueCapacity;
    }
}
