/**
 * Package: com.lopframework.lop.error
 * Description: 
 */
package com.lopframework.lop.error;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:  
 * Date: 2017年8月8日 下午5:34:09
 * @author wufenyun 
 */
public class GenericError implements LopError {
    
    private String code;
    private String msg;
    private List<LopError> subErrors;
    
    public GenericError(String code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }
    
    @Override
    public List<LopError> getSubErrors() {
        return subErrors;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void addSubError(LopError error) {
        if(null == subErrors) {
            subErrors = new ArrayList<>();
        }
        this.subErrors.add(error);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setSubErrors(List<LopError> subErrors) {
        this.subErrors = subErrors;
    }

}
