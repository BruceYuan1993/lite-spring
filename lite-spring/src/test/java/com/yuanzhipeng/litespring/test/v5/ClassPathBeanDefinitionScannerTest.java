package com.yuanzhipeng.litespring.test.v5;

import org.junit.Assert;
import org.junit.Test;

import com.yuanzhipeng.litespring.beans.BeanDefinition;
import com.yuanzhipeng.litespring.beans.factory.support.DefaultBeanFactory;
import com.yuanzhipeng.litespring.context.annotation.ClassPathBeanDefinitionScanner;
import com.yuanzhipeng.litespring.context.annotation.ScannedGenericBeanDefinition;
import com.yuanzhipeng.litespring.core.annotation.AnnotationAttributes;
import com.yuanzhipeng.litespring.core.type.AnnotationMetadata;
import com.yuanzhipeng.litespring.stereotype.Component;

public class ClassPathBeanDefinitionScannerTest {
    @Test
    public void testParseScannedBean() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        String basePackages = "com.yuanzhipeng.litespring.service.v5, com.yuanzhipeng.litespring.dao.v5";
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(factory);
        scanner.doScan(basePackages);
        
        String annotation = Component.class.getName();
        
        {
            BeanDefinition bd = factory.getBeanDefinition("petStore");
            Assert.assertTrue(bd instanceof ScannedGenericBeanDefinition);
            ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition)bd;
            AnnotationMetadata amd = sbd.getMetadata();
            
            
            Assert.assertTrue(amd.hasAnnotation(annotation));       
            AnnotationAttributes attributes = amd.getAnnotationAttributes(annotation);      
            Assert.assertEquals("petStore", attributes.get("value"));
        }
        {
            BeanDefinition bd = factory.getBeanDefinition("accountDao");
            Assert.assertTrue(bd instanceof ScannedGenericBeanDefinition);
            ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition)bd;            
            AnnotationMetadata amd = sbd.getMetadata();
            Assert.assertTrue(amd.hasAnnotation(annotation));
        }
        {
            BeanDefinition bd = factory.getBeanDefinition("itemDao");
            Assert.assertTrue(bd instanceof ScannedGenericBeanDefinition);
            ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition)bd;            
            AnnotationMetadata amd = sbd.getMetadata();
            Assert.assertTrue(amd.hasAnnotation(annotation));
        }
    }
}
