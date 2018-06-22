package com.yuanzhipeng.litespring.test.v1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.yuanzhipeng.litespring.beans.BeanDefinition;
import com.yuanzhipeng.litespring.beans.factory.BeanCreationException;
import com.yuanzhipeng.litespring.beans.factory.BeanDefinitionStoreException;
import com.yuanzhipeng.litespring.beans.factory.support.DefaultBeanFactory;
import com.yuanzhipeng.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import com.yuanzhipeng.litespring.core.io.ClassPathResource;
import com.yuanzhipeng.litespring.service.v1.PetStroeService;

public class BeanFactoryTest {

    DefaultBeanFactory factory = null;
    XmlBeanDefinitionReader reader = null;
    
    @Before
    public void setUp() {
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);
    }
    
    @Test
    public void testGetBean() {
        reader.loadBeanDefinition(new ClassPathResource("petstore-v1.xml"));
        BeanDefinition bd = factory.getBeanDefinition("petStore");
        assertTrue(bd.isSingleton());
        assertFalse(bd.isPrototype());
        assertEquals(BeanDefinition.SCOPE_DEFAULT,bd.getScope());
        assertEquals("com.yuanzhipeng.litespring.service.v1.PetStroeService", bd.getBeanClassName());
        
        PetStroeService petStrore = (PetStroeService)factory.getBean("petStore");
        assertNotNull(petStrore);
        
        PetStroeService petStrore1 = (PetStroeService)factory.getBean("petStore");
        
        assertTrue(petStrore == petStrore1);
    }
    
    @Test(expected = BeanCreationException.class)
    public void testInvalidBean() {
        reader.loadBeanDefinition(new ClassPathResource("petstore-v1.xml"));
        BeanDefinition bd = factory.getBeanDefinition("petStore");
        factory.getBean("invalidBean");
    }
    
    @Test(expected = BeanDefinitionStoreException.class)
    public void testInvalidXML() {
        reader.loadBeanDefinition(new ClassPathResource("invalid.xml"));
    }
}
