/**
 * Package: com.lopframework.lop.service.handler
 * Description: 
 */
package com.lopframework.lop.service.handler;

import com.lopframework.lop.common.error.LopError;
import com.lopframework.lop.service.request.Request;

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
    LopError proccess(Request request);
}
