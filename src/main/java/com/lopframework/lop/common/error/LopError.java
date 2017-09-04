/**
 * Package: com.lopframework.lop.error
 * Description: 
 */
package com.lopframework.lop.common.error;

import java.util.List;

/**
 * Description:  
 * Date: 2017年8月8日 下午5:28:38
 * @author wufenyun 
 */
public interface LopError {
    
    String getCode();
    
    String getMessage();
    
    List<LopError> getSubErrors();
    
    void addSubError(LopError error);
}
