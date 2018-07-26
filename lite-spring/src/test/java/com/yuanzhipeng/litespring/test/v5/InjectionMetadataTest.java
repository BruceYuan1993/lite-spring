package com.yuanzhipeng.litespring.test.v5;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.yuanzhipeng.litespring.beans.factory.annotation.AutowiredFieldElement;
import com.yuanzhipeng.litespring.beans.factory.annotation.InjectionElement;
import com.yuanzhipeng.litespring.beans.factory.annotation.InjectionMetadata;
import com.yuanzhipeng.litespring.beans.factory.support.DefaultBeanFactory;
import com.yuanzhipeng.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import com.yuanzhipeng.litespring.core.io.ClassPathResource;
import com.yuanzhipeng.litespring.dao.v5.AccountDao;
import com.yuanzhipeng.litespring.dao.v5.ItemDao;
import com.yuanzhipeng.litespring.service.v5.PetStroeService;

public class InjectionMetadataTest {
    @Test
    public void testInjection() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinition(new ClassPathResource("petstore-v5.xml"));

        Class<?> clz = PetStroeService.class;
        List<InjectionElement> elements = new LinkedList<>();
        try {
            {
                Field f = clz.getDeclaredField("accountDao");
                InjectionElement injectionEle = new AutowiredFieldElement(f, true, factory);
                elements.add(injectionEle);
            }

            {
                Field f = clz.getDeclaredField("itemDao");

                InjectionElement injectionEle = new AutowiredFieldElement(f, true, factory);
                elements.add(injectionEle);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        InjectionMetadata metadata = new InjectionMetadata(PetStroeService.class, elements);
        PetStroeService petStroe = new PetStroeService();
        metadata.inject(petStroe);
        Assert.assertTrue(petStroe.getAccountDao() instanceof AccountDao);
        Assert.assertTrue(petStroe.getItemDao() instanceof ItemDao);
    }
}
