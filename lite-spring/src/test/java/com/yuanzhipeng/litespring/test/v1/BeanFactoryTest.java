package com.yuanzhipeng.litespring.test.v1;

import static org.junit.Assert.*;

import org.junit.Test;

import com.yuanzhipeng.litespring.beans.BeanDefinition;
import com.yuanzhipeng.litespring.beans.factory.BeanCreationException;
import com.yuanzhipeng.litespring.beans.factory.BeanDefinitionStoreException;
import com.yuanzhipeng.litespring.beans.factory.BeanFactory;
import com.yuanzhipeng.litespring.beans.factory.support.DefaultBeanFactory;
import com.yuanzhipeng.litespring.service.v1.PetStroeService;

public class BeanFactoryTest {

    @Test
    public void testGetBean() {
        BeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");
        BeanDefinition bd = factory.getBeanDefinition("petStore");
        
        assertEquals("com.yuanzhipeng.litespring.service.v1.PetStroeService", bd.getBeanClassName());
        
        PetStroeService petStrore = (PetStroeService)factory.getBean("petStore");
        assertNotNull(petStrore);
    }
    
    @Test(expected = BeanCreationException.class)
    public void testInvalidBean() {
        BeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");
        factory.getBean("invalidBean");
    }
    
    @Test(expected = BeanDefinitionStoreException.class)
    public void testInvalidXML() {
        new DefaultBeanFactory("invalid.xml");
    }
}
