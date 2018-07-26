package com.yuanzhipeng.litespring.test.v5;

import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.Test;

import com.yuanzhipeng.litespring.beans.factory.config.DependencyDescriptor;
import com.yuanzhipeng.litespring.beans.factory.support.DefaultBeanFactory;
import com.yuanzhipeng.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import com.yuanzhipeng.litespring.core.io.ClassPathResource;
import com.yuanzhipeng.litespring.dao.v5.AccountDao;
import com.yuanzhipeng.litespring.service.v5.PetStroeService;

public class DependencyDescriptorTest {
    @Test
    public void testResolveDependency() throws Exception {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinition(new ClassPathResource("petstore-v5.xml"));
        
        Field f = PetStroeService.class.getDeclaredField("accountDao");
        DependencyDescriptor descriptor = new DependencyDescriptor(f, true);
        Object o = factory.resolveDependency(descriptor);
        Assert.assertTrue(o instanceof AccountDao);
    }
}
