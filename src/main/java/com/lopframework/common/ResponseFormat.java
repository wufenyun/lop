/**
 * Package: com.lopframework.lop.constant
 * Description: 
 */
package com.lopframework.common;

/**
 * Description:  
 * Date: 2017年8月19日 上午11:14:47
 * @author wufenyun 
 */
public enum ResponseFormat {
    
    JSON("json","application/json;charset=utf-8");
    
    public String format;
    public String mimetype;
    
    ResponseFormat(String format,String mimetype) {
        this.format = format;
        this.mimetype = mimetype;
    }
    
    /** 
     * Description: 通过格式获取其枚举对象 
     * @param format
     * @return
     */
    public static ResponseFormat getFormat(String format) {
        ResponseFormat[] fms = ResponseFormat.values();
        for(ResponseFormat fm:fms) {
            if(fm.format.equals(format)) {
                return fm;
            }
        }
        return null;
    }
    
    /** 
     * Description: 是否是支持的格式 
     * @param type
     * @return
     */
    public static boolean isSurpportFormat(String type) {
        ResponseFormat[] fms = ResponseFormat.values();
        for(ResponseFormat fm:fms) {
            if(fm.format.equals(type)) {
                return true;
            }
        }
        return false;
    }
}
