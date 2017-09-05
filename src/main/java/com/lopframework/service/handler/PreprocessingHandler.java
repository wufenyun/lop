/**
 * Package: com.lopframework.lop.service.handler
 * Description: 
 */
package com.lopframework.service.handler;

import com.lopframework.common.error.ServiceError;
import com.lopframework.service.request.Request;

/**
 * Description: 预处理执行器，在服务正式调用之前调用的处理器
 * Date: 2017年8月8日 下午3:35:46
 * @author wufenyun 
 */
public interface PreprocessingHandler {
    
    /** 
     * 执行方法
     * @param context
     * @return 处理成功返回true，处理失败或者异常返回false
     */
    ServiceError proccess(Request request);
}
