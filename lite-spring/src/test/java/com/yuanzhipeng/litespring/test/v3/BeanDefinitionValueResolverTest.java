package com.yuanzhipeng.litespring.test.v3;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.yuanzhipeng.litespring.beans.factory.config.RuntimeBeanReference;
import com.yuanzhipeng.litespring.beans.factory.config.TypedStringValue;
import com.yuanzhipeng.litespring.beans.factory.support.BeanDefinitionValueResolver;
import com.yuanzhipeng.litespring.beans.factory.support.DefaultBeanFactory;
import com.yuanzhipeng.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import com.yuanzhipeng.litespring.core.io.ClassPathResource;
import com.yuanzhipeng.litespring.dao.v2.AccountDao;

public class BeanDefinitionValueResolverTest {
    
    DefaultBeanFactory factory = null;
    XmlBeanDefinitionReader reader = null;
    BeanDefinitionValueResolver resolver = null;
    
    @Before
    public void setUp() {
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinition(new ClassPathResource("petstore-v2.xml"));
        resolver = new BeanDefinitionValueResolver(factory);
    }
    
    
    @Test
    public void testResolveRuntimeBeanReference() {
        RuntimeBeanReference reference = new RuntimeBeanReference("accountDao");
        Object value = resolver.resolveValueIfNecessary(reference);
        
        Assert.assertNotNull(value);
        Assert.assertTrue(value instanceof AccountDao);
    }
    
    @Test
    public void testResolveTypedStringValue() {        
        String str = "test";
        TypedStringValue strValue = new TypedStringValue(str);
        Object value = resolver.resolveValueIfNecessary(strValue);
        
        Assert.assertNotNull(value);
        Assert.assertEquals(str, value);
    }
}
