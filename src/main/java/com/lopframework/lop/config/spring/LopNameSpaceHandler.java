/**
 * Package: com.lopframework.lop.schema
 * Description: 
 */
package com.lopframework.lop.config.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Description:  
 * Date: 2017年8月9日 下午4:47:48
 * @author wufenyun 
 */
public class LopNameSpaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("context-config", new DispatcherDefinitionParser());
    }
}
