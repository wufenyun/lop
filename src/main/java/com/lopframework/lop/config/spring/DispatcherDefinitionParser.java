/**
 * Package: com.lopframework.lop.schema
 * Description: 
 */
package com.lopframework.lop.config.spring;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.parsing.CompositeComponentDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

import com.lopframework.lop.common.threadpool.ThreadPoolConfig;
import com.lopframework.lop.config.FrameConfig;
import com.lopframework.lop.servlet.AnnotationServiceDispatcher;

/**
 * Description: lop框架配置解析器
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
        RootBeanDefinition serviceDispacherrDef = new RootBeanDefinition(AnnotationServiceDispatcher.class);
        String serviceDispatcherName = element.getAttribute("id");
        if (StringUtils.hasText(serviceDispatcherName)) {
            parserContext.getRegistry().registerBeanDefinition(serviceDispatcherName, serviceDispacherrDef);
        } else {
            serviceDispatcherName = parserContext.getReaderContext().registerWithGeneratedName(serviceDispacherrDef);
        }
        parserContext.registerComponent(new BeanComponentDefinition(serviceDispacherrDef, serviceDispatcherName));
        
        //解析线程池
        RuntimeBeanReference threadPoolExecutorRef = parseExecutor(element,parserContext);
        serviceDispacherrDef.getPropertyValues().add("executor", threadPoolExecutorRef);
        
        //解析其他系统配置
        RuntimeBeanReference framConfigRef = parseFrameConfig(element, parserContext);
        serviceDispacherrDef.getPropertyValues().add("frameConfig", framConfigRef);
        return null;
    }
    
    /** 
     * Description: 解析框架配置信息
     * @param element
     * @param parserContext
     * @return
     */
    private RuntimeBeanReference parseFrameConfig(Element element, ParserContext parserContext) {
        RootBeanDefinition frameConfigDef = new RootBeanDefinition(FrameConfig.class);
        
        String timeoutLimitSenconds = element.getAttribute("service-timeout-seconds");
        frameConfigDef.getPropertyValues().add("timeoutLimitSeconds",
                StringUtils.hasText(timeoutLimitSenconds) ? timeoutLimitSenconds : FrameConfig.DEFAULT_TIMEOUT);
        
        String configName = parserContext.getReaderContext().registerWithGeneratedName(frameConfigDef);
        parserContext.registerComponent(new BeanComponentDefinition(frameConfigDef, configName));
        return new RuntimeBeanReference(configName);
    }
    
    /** 
     * Description:  解析线程池配置并返回线程池对象引用
     * @param element
     * @param parserContext
     * @return
     */
    private RuntimeBeanReference parseExecutor(Element element, ParserContext parserContext) {
        RootBeanDefinition executorDef =
                new RootBeanDefinition(org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean.class);
        String executorName = parserContext.getReaderContext().registerWithGeneratedName(executorDef);
        MutablePropertyValues values = new MutablePropertyValues();
        
        String corePoolSize = element.getAttribute("threadpool-core-size");
        values.addPropertyValue("corePoolSize", StringUtils.hasText(corePoolSize)?corePoolSize:ThreadPoolConfig.DEFAULT_CORE_POOL_SIZE);

        String maxPoolSize = element.getAttribute("threadpool-max-size");
        values.addPropertyValue("maxPoolSize", StringUtils.hasText(maxPoolSize)?maxPoolSize:ThreadPoolConfig.DEFAULT_MAX_POOL_SIZE);

        String keepAliveSeconds = element.getAttribute("threadpool-alive-seconds");
        values.addPropertyValue("keepAliveSeconds", StringUtils.hasText(keepAliveSeconds)?keepAliveSeconds:ThreadPoolConfig.DEFAULT_KEEP_ALIVE_SECONDS);

        String queueCapacity = element.getAttribute("threadpool-queue-capacity");
        values.addPropertyValue("queueCapacity", StringUtils.hasText(queueCapacity)?queueCapacity:ThreadPoolConfig.DEFAULT_QUENE_CAPACITY);

        executorDef.getPropertyValues().addPropertyValues(values);
        parserContext.registerComponent(new BeanComponentDefinition(executorDef, executorName));
        return new RuntimeBeanReference(executorName);
    }

}
