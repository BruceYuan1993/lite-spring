package com.yuanzhipeng.litespring.test.v4;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.yuanzhipeng.litespring.beans.BeanDefinition;
import com.yuanzhipeng.litespring.beans.factory.support.ConstructorResolver;
import com.yuanzhipeng.litespring.beans.factory.support.DefaultBeanFactory;
import com.yuanzhipeng.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import com.yuanzhipeng.litespring.core.io.ClassPathResource;
import com.yuanzhipeng.litespring.service.v4.PetStroeService;

public class ConstructorResolverTest {
    
    @Test
    public void testAutowireConstructor() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinition(new ClassPathResource("petstore-v4.xml"));
        BeanDefinition bd = factory.getBeanDefinition("petStore");
        
        ConstructorResolver resolver = new ConstructorResolver(factory);
        PetStroeService petStrore = (PetStroeService)resolver.autowireConstructor(bd);
        
        assertNotNull(petStrore.getAccountDao());
        assertNotNull(petStrore.getItemDao());
        assertTrue(1 == petStrore.getVersion());
    }
    
}
