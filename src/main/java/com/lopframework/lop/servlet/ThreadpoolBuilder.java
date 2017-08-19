/**
 * Package: com.lopframework.lop.servlet
 * Description: 
 */
package com.lopframework.lop.servlet;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description:  
 * Date: 2017年8月19日 
 * @author wufenyun 
 */
public class ThreadpoolBuilder {
    
    private final static Logger logger = LoggerFactory.getLogger(ThreadpoolBuilder.class);
    
    private ThreadPoolExecutor executor;
    private ThreadPoolConfig threadPoolConfig;
    
    public ThreadPoolExecutor buildExecutor() {
        threadPoolConfig = (null==threadPoolConfig)?new ThreadPoolConfig(100, 200, 5):threadPoolConfig;
        
        logger.info("ThreadPoolExecutor message:corePoolSize={},maximumPoolSize={},keepAliveTime={}s"
                ,threadPoolConfig.getCorePoolSize(),threadPoolConfig.getMaximumPoolSize(),threadPoolConfig.getKeepAliveTime());
        
        if (null == executor) {
            executor = new ThreadPoolExecutor(threadPoolConfig.getCorePoolSize(), threadPoolConfig.getMaximumPoolSize(),
                    threadPoolConfig.getKeepAliveTime(), threadPoolConfig.getUnit(), new LinkedBlockingQueue<Runnable>());
        } else {
            executor.setCorePoolSize(threadPoolConfig.getCorePoolSize());
            executor.setMaximumPoolSize(threadPoolConfig.getMaximumPoolSize());
            executor.setKeepAliveTime(threadPoolConfig.getKeepAliveTime(), threadPoolConfig.getUnit());
        }
        return executor;
    }
    
    
}

/**
 * Description:  线程池配置类
 * Date: 2017年8月19日
 * @author wufenyun 
 */
class ThreadPoolConfig {
    
    private int corePoolSize;
    private int maximumPoolSize;
    private long keepAliveTime;
    private TimeUnit unit = TimeUnit.SECONDS;

    public ThreadPoolConfig(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime;
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
}
