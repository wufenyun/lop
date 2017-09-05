/**
 * Package: com.lopframework.lop.servlet
 * Description: 
 */
package com.lopframework.common.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: 线程池构造器
 * Date: 2017年8月19日 
 * @author wufenyun 
 */
public class ThreadpoolBuilder {
    
    private final static Logger logger = LoggerFactory.getLogger(ThreadpoolBuilder.class);
    
    public static final int DEFAULT_CORE_POOL_SIZE = 30;

    public static final int DEFAULT_MAX_POOL_SIZE = 120;

    public static final int DEFAULT_KEEP_ALIVE_SECONDS = 60;

    public static final int DEFAULT_QUENE_CAPACITY = 0;
    
    /** 
     * Description: 构建线程池
     * @param threadPoolConfig 线程池配置信息
     * @return
     */
    public static ThreadPoolExecutor buildExecutor(ThreadPoolConfig threadPoolConfig) {
        threadPoolConfig = (null == threadPoolConfig) ? new ThreadPoolConfig(DEFAULT_CORE_POOL_SIZE,
                DEFAULT_MAX_POOL_SIZE, DEFAULT_KEEP_ALIVE_SECONDS, DEFAULT_QUENE_CAPACITY) : threadPoolConfig;

        logger.info("ThreadPoolExecutor message:corePoolSize={},maximumPoolSize={},keepAliveTime={}s,workQueueLength={}"
                ,threadPoolConfig.getCorePoolSize(),threadPoolConfig.getMaximumPoolSize(),threadPoolConfig.getKeepAliveTime(),threadPoolConfig.getQueueCapacity());
        
        LinkedBlockingQueue<Runnable> taskQueue = null;
        if(threadPoolConfig.getQueueCapacity() > 0) {
            taskQueue = new LinkedBlockingQueue<Runnable>(threadPoolConfig.getQueueCapacity());
        }
        
        return new ThreadPoolExecutor(threadPoolConfig.getCorePoolSize(), threadPoolConfig.getMaximumPoolSize(),
                    threadPoolConfig.getKeepAliveTime(), threadPoolConfig.getUnit(), taskQueue);
    }
    
    
}


