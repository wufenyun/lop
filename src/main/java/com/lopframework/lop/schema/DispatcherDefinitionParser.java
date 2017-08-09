/**
 * Package: com.lopframework.lop.schema
 * Description: 
 */
package com.lopframework.lop.schema;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.parsing.CompositeComponentDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

import com.lopframework.lop.servlet.AnnotationServiceDispatcher;

/**
 * Description:  
 * Date: 2017年8月9日 下午4:48:35
 * @author wufenyun 
 */
public class DispatcherDefinitionParser implements BeanDefinitionParser {

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        Object source = parserContext.extractSource(element);
        CompositeComponentDefinition compDefinition = new CompositeComponentDefinition(element.getTagName(), source);
        parserContext.pushContainingComponent(compDefinition);
        
        //注册AnnotationServiceDispatcher Bean
        RootBeanDefinition serviceRouterDef = new RootBeanDefinition(AnnotationServiceDispatcher.class);
        String serviceDispatcherName = element.getAttribute("id");
        if (StringUtils.hasText(serviceDispatcherName)) {
            parserContext.getRegistry().registerBeanDefinition(serviceDispatcherName, serviceRouterDef);
        } else {
            serviceDispatcherName = parserContext.getReaderContext().registerWithGeneratedName(serviceRouterDef);
        }
        parserContext.registerComponent(new BeanComponentDefinition(serviceRouterDef, serviceDispatcherName));

        return null;
    }
}
