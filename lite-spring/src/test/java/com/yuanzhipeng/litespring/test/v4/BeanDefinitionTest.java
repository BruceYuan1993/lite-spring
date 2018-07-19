package com.yuanzhipeng.litespring.test.v4;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.yuanzhipeng.litespring.beans.BeanDefinition;
import com.yuanzhipeng.litespring.beans.ConstructorArgument;
import com.yuanzhipeng.litespring.beans.ConstructorArgument.ValueHolder;
import com.yuanzhipeng.litespring.beans.factory.config.RuntimeBeanReference;
import com.yuanzhipeng.litespring.beans.factory.config.TypedStringValue;
import com.yuanzhipeng.litespring.beans.factory.support.DefaultBeanFactory;
import com.yuanzhipeng.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import com.yuanzhipeng.litespring.core.io.ClassPathResource;

public class BeanDefinitionTest {
    @Test
    public void testGetBean() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinition(new ClassPathResource("petstore-v4.xml"));
        
        BeanDefinition bd = factory.getBeanDefinition("petStore");
        Assert.assertTrue("com.yuanzhipeng.litespring.service.v4.PetStroeService".equals(bd.getBeanClassName()));

        ConstructorArgument args = bd.getConstructorArgument();
        List<ValueHolder> valueHolder = args.getArgumentValues();
        
        Assert.assertTrue(valueHolder.size() == 3);
        RuntimeBeanReference ref1 = (RuntimeBeanReference)valueHolder.get(0).getValue();
        Assert.assertTrue("accountDao".equals(ref1.getBeanName()));
        
        
        RuntimeBeanReference ref2 = (RuntimeBeanReference)valueHolder.get(1).getValue();
        Assert.assertTrue("itemDao".equals(ref2.getBeanName()));
        
        
        TypedStringValue ref3 = (TypedStringValue)valueHolder.get(2).getValue();
        Assert.assertTrue("1".equals(ref3.getValue()));
        
    }
}
