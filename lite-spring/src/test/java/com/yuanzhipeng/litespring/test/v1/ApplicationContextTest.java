package com.yuanzhipeng.litespring.test.v1;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.yuanzhipeng.litespring.context.ApplicationContext;
import com.yuanzhipeng.litespring.context.support.ClassPathXmlApplicationContext;
import com.yuanzhipeng.litespring.context.support.FileSystemXmlApplicationContext;
import com.yuanzhipeng.litespring.service.v1.PetStroeService;

public class ApplicationContextTest {
    @Test
    public void testGetBean() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v1.xml");
        PetStroeService petStrore = (PetStroeService)ctx.getBean("petStore");
        assertNotNull(petStrore);
    }
    
    @Test
    public void testGetBeanFromFileSystemContext() {
        ApplicationContext ctx = new FileSystemXmlApplicationContext("D:\\spring\\lite-spring\\lite-spring\\src\\test\\resources\\petstore-v1.xml");
        PetStroeService petStrore = (PetStroeService)ctx.getBean("petStore");
        assertNotNull(petStrore);
    }
}
