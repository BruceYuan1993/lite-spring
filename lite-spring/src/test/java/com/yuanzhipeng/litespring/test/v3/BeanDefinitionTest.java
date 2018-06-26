package com.yuanzhipeng.litespring.test.v3;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.yuanzhipeng.litespring.beans.BeanDefinition;
import com.yuanzhipeng.litespring.beans.PropertyValue;
import com.yuanzhipeng.litespring.beans.factory.config.RuntimeBeanReference;
import com.yuanzhipeng.litespring.beans.factory.support.DefaultBeanFactory;
import com.yuanzhipeng.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import com.yuanzhipeng.litespring.core.io.ClassPathResource;

public class BeanDefinitionTest {
    @Test
    public void testGetBean() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinition(new ClassPathResource("petstore-v2.xml"));
        BeanDefinition bd = factory.getBeanDefinition("petStore");
        List<PropertyValue> pvs = bd.getPropertyValues();
        Assert.assertTrue(pvs.size() == 4);
        {
            PropertyValue pv = this.getPropertyValue("accountDao", pvs);
            Assert.assertNotNull(pv);
            Assert.assertTrue(pv.getValue() instanceof RuntimeBeanReference);
        }
        {
            PropertyValue pv = this.getPropertyValue("itemDao", pvs);
            Assert.assertNotNull(pv);
            Assert.assertTrue(pv.getValue() instanceof RuntimeBeanReference);
        }
    }
    
    public PropertyValue getPropertyValue(String name, List<PropertyValue> pvs){
        for (PropertyValue item : pvs) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }
}
