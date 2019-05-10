package com.yuanzhipeng.litespring.test.v6;

import java.lang.reflect.Method;

import com.yuanzhipeng.litespring.beans.factory.BeanFactory;
import com.yuanzhipeng.litespring.beans.factory.config.AspectInstanceFactory;
import com.yuanzhipeng.litespring.beans.factory.support.DefaultBeanFactory;
import com.yuanzhipeng.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import com.yuanzhipeng.litespring.core.io.ClassPathResource;
import com.yuanzhipeng.litespring.core.io.Resource;
import com.yuanzhipeng.litespring.tx.TransactionManager;


public class AbstractV6Test {
        
    protected BeanFactory getBeanFactory(String configFile){
        DefaultBeanFactory defaultBeanFactory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(defaultBeanFactory);
        Resource resource = new ClassPathResource(configFile);
        reader.loadBeanDefinition(resource);   
        return  defaultBeanFactory;     
    }
    
    protected  Method getAdviceMethod( String methodName) throws Exception{
        return TransactionManager.class.getMethod(methodName);      
    }
    
    protected  AspectInstanceFactory getAspectInstanceFactory(String targetBeanName){
        AspectInstanceFactory factory = new AspectInstanceFactory();
        factory.setAspectBeanName(targetBeanName);      
        return factory;
    }
    
    
}
